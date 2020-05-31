package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.AcademicPeriod;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.AcademicPeriodService;

import javax.inject.Inject;
import java.util.List;

public class AcademicPeriodController extends Controller {
    private final AcademicPeriodService service;

    @Inject
    public AcademicPeriodController(final AcademicPeriodService service) {
        this.service = service;
    }

    public Result link(String classId, String studentId) {
        this.service.link(classId, studentId);

        return ok();
    }

    public Result unlink(String classId, String studentId) {
        this.service.unlink(classId, studentId);

        return ok();
    }

    public Result getAllStudentsByClassId(String classId) {
        List<AcademicPeriod> out = this.service.getAllStudentsByClassId(classId);
        JsonNode json = Json.toJson(out);

        return ok(json);
    }

    public Result getAllClassesByStudentId(String studentId) {
        List<AcademicPeriod> out = this.service.getAllClassesByStudentId(studentId);
        JsonNode json = Json.toJson(out);

        return ok(json);
    }
}
