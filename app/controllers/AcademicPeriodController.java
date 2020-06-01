package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.AcademicPeriod;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.AcademicPeriodService;

import javax.inject.Inject;
import java.util.List;

/**
 * This controller contains actions to handle HTTP requests
 * to do CRUD operations with enroll/disenroll operations for students to/from classes.
 */
public class AcademicPeriodController extends Controller {
    private final AcademicPeriodService service;

    @Inject
    public AcademicPeriodController(final AcademicPeriodService service) {
        this.service = service;
    }

    /**
     * An action that enroll a student to a given class.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>POST</code> request with a path of
     * <code>/class/:classId/student/:studentId/enroll</code>.
     */
    public Result link(String classId, String studentId) {
        this.service.link(classId, studentId);

        return ok();
    }

    /**
     * An action that disenroll a student from a class.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>POST</code> request with a path of
     * <code>/class/:classId/student/:studentId/disenroll</code>.
     */
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
