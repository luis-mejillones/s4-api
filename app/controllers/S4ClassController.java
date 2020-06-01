package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import models.S4Class;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.GenericService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * This controller contains actions to handle HTTP requests
 * to do CRUD operations with classes.
 */
public class S4ClassController extends Controller {
    private final GenericService<S4Class> service;

    @Inject
    public S4ClassController(final GenericService<S4Class> service) {
        this.service = service;
    }

    /**
     * An action that create a class with json payload.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>POST</code> request with a path of <code>/class</code>.
     */
    public Result create(Http.Request request) {
        JsonNode body = request.body().asJson();
        if (body == null) {
            return badRequest(this.service.getResponseMessage(Http.Status.BAD_REQUEST, "Expecting Json data"));
        }

        S4Class s4Class;
        try {
            s4Class = Json.mapper().treeToValue(body, S4Class.class);
        } catch (JsonProcessingException e) {
            return badRequest(
                    this.service.getResponseMessage(Http.Status.BAD_REQUEST, "Wrong json data: " + e.getMessage())
            );
        }

        S4Class out = this.service.create(s4Class);
        JsonNode json = Json.toJson(out);

        return created(json);
    }

    public Result getAll() {
        List<S4Class> out = this.service.getAll();
        JsonNode json = Json.toJson(out);

        return ok(json);
    }

    public Result getById(String id) {
        Optional<S4Class> out = this.service.getById(id);
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

        S4Class newData;
        try {
            newData = Json.mapper().treeToValue(body, S4Class.class);
        } catch (JsonProcessingException e) {
            return badRequest(
                    this.service.getResponseMessage(Http.Status.BAD_REQUEST, "Wrong json data: " + e.getMessage())
            );
        }

        this.service.update(id, newData);

        return ok();
    }
}
