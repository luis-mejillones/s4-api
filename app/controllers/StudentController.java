package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import models.Student;
import org.apache.commons.lang3.StringUtils;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.StudentService;

import javax.inject.Inject;
import java.util.Optional;

public class StudentController extends Controller {
    private final StudentService service;

    @Inject
    public StudentController(final StudentService service) {
        this.service = service;
    }

    public Result create(Http.Request request) {
        JsonNode body = request.body().asJson();
        if (body == null) {
            return badRequest(this.service.getResponseMessage(Http.Status.BAD_REQUEST, "Expecting Json data"));
        }

        Student student = null;
        try {
            student = Json.mapper().treeToValue(body, Student.class);
        } catch (JsonProcessingException e) {
            return badRequest(
                    this.service.getResponseMessage(Http.Status.BAD_REQUEST, "Wrong json data: " + e.getMessage())
            );
        }

        Student out = this.service.create(student);
        JsonNode json = Json.toJson(out);

        return created(this.service.getResponseMessage(Http.Status.CREATED, json.toString()));
    }

    public Result getById(String id) {
        Optional<Student> out = this.service.getById(id);
        if (!out.isPresent()) {
            return notFound(this.service.getResponseMessage(Http.Status.NOT_FOUND, "Student not found"));
        }

        JsonNode json = Json.toJson(out);

        return ok(this.service.getResponseMessage(Http.Status.OK, json.toString()));
    }

    public Result delete(String id) {
        Boolean out = this.service.delete(id);
        if (!out) {
            return notFound(this.service.getResponseMessage(Http.Status.NOT_FOUND, "Student not found"));
        }

        return ok(this.service.getResponseMessage(Http.Status.OK, StringUtils.EMPTY));
    }

    public Result updatePatch(String id, Http.Request request) {
        JsonNode body = request.body().asJson();
        if (body == null) {
            return badRequest(this.service.getResponseMessage(Http.Status.BAD_REQUEST, "Expecting Json data"));
        }

        Student newData = null;
        try {
            newData = Json.mapper().treeToValue(body, Student.class);
        } catch (JsonProcessingException e) {
            return badRequest(
                    this.service.getResponseMessage(Http.Status.BAD_REQUEST, "Wrong json data: " + e.getMessage())
            );
        }

        this.service.updatePatch(id, newData);

        return ok(this.service.getResponseMessage(Http.Status.OK, StringUtils.EMPTY));
    }
}
