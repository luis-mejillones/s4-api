package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import models.Student;
import org.bson.Document;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.StudentService;

import javax.inject.Inject;

public class StudentController extends Controller {
    private final StudentService service;

    @Inject
    public StudentController(final StudentService service) {
        this.service = service;
    }

    public Result create(Http.Request request) {
        JsonNode body = request.body().asJson();
        if (body == null) {
            return badRequest("Expecting Json data");
        }

        Student student = null;
        try {
            student = Json.mapper().treeToValue(body, Student.class);
        } catch (JsonProcessingException e) {
            return badRequest("Wrong json data: " + e.getMessage());
        }

        Student out = this.service.create(student);
        JsonNode json = Json.toJson(out);

        return created(json);
    }

    public Result getById(String id) {
        Student out = this.service.getById(id);
        if (out == null) {
            return notFound("Student not found");
        }

        JsonNode json = Json.toJson(out);

        return ok(json);
    }

    public Result delete(String id) {
        Boolean out = this.service.delete(id);
        if (!out) {
            return notFound("Student not found");
        }

        JsonNode json = Json.toJson(out);

        return ok();
    }

    public Result updatePatch(String id, Http.Request request) {
        JsonNode body = request.body().asJson();
        if (body == null) {
            return badRequest("Expecting Json data");
        }

        Student newData = null;
        try {
            newData = Json.mapper().treeToValue(body, Student.class);
        } catch (JsonProcessingException e) {
            return badRequest("Wrong json data: " + e.getMessage());
        }

        Student out = this.service.updatePatch(id, newData);
        JsonNode json = Json.toJson(out);

        return ok();
    }
}
