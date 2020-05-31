package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import models.Student;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.GenericService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class StudentController extends Controller {
    private final GenericService<Student> service;

    @Inject
    public StudentController(final GenericService<Student> service) {
        this.service = service;
    }

    public Result create(Http.Request request) {
        JsonNode body = request.body().asJson();
        if (body == null) {
            return badRequest(this.service.getResponseMessage(Http.Status.BAD_REQUEST, "Expecting Json data"));
        }

        Student student;
        try {
            student = Json.mapper().treeToValue(body, Student.class);
        } catch (JsonProcessingException e) {
            return badRequest(
                    this.service.getResponseMessage(Http.Status.BAD_REQUEST, "Wrong json data: " + e.getMessage())
            );
        }

        Student out = this.service.create(student);
        JsonNode json = Json.toJson(out);

        return created(json);
    }

    public Result getAll() {
        List<Student> out = this.service.getAll();
        JsonNode json = Json.toJson(out);

        return ok(json);
    }

    public Result getById(String id) {
        Optional<Student> out = this.service.getById(id);
        if (!out.isPresent()) {
            return notFound(this.service.getResponseMessage(Http.Status.NOT_FOUND, "Student not found"));
        }

        JsonNode json = Json.toJson(out);

        return ok(json);
    }

    public Result delete(String id) {
        Boolean out = this.service.delete(id);
        if (!out) {
            return notFound(this.service.getResponseMessage(Http.Status.NOT_FOUND, "Student not found"));
        }

        return ok();
    }

    public Result update(String id, Http.Request request) {
        JsonNode body = request.body().asJson();
        if (body == null) {
            return badRequest(this.service.getResponseMessage(Http.Status.BAD_REQUEST, "Expecting Json data"));
        }

        Student newData;
        try {
            newData = Json.mapper().treeToValue(body, Student.class);
        } catch (JsonProcessingException e) {
            return badRequest(
                    this.service.getResponseMessage(Http.Status.BAD_REQUEST, "Wrong json data: " + e.getMessage())
            );
        }

        this.service.update(id, newData);

        return ok();
    }
}
