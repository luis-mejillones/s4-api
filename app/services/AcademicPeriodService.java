package services;

import models.AcademicPeriod;
import org.slf4j.Logger;

import java.util.List;

public class AcademicPeriodService {
    private final AcademicPeriodRepository repository;
    private final Logger logger;

    public AcademicPeriodService(
            final AcademicPeriodRepository repository,
            final Logger logger
    ) {
        this.repository = repository;
        this.logger = logger;
    }

    public void link(String classId, String studentId) {
        this.repository.link(classId, studentId);
    }

    public void unlink(String classId, String studentId) {
        this.repository.unlink(classId, studentId);
    }

    public List<AcademicPeriod> getAllStudentsByClassId(String classId) {
        return this.repository.getAllStudentsByClassId(classId);
    }

    public List<AcademicPeriod> getAllClassesByStudentId(String studentId) {
        return this.repository.getAllClassesByStudentId(studentId);
    }
}
