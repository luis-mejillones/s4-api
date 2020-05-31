package services;

import models.AcademicPeriod;
import org.bson.Document;
import org.slf4j.Logger;
import utils.IdGen;
import utils.Persistence;
import utils.S4Serializable;

import java.util.ArrayList;
import java.util.List;

public class AcademicPeriodRepository {
    private final Persistence<S4Serializable> persistence;
    private final Logger logger;

    public AcademicPeriodRepository(final Persistence<S4Serializable> persistence, final Logger logger) {
        this.persistence = persistence;
        this.logger = logger;
    }

    public void link(String classId, String studentId) {
        AcademicPeriod academicPeriod = new AcademicPeriod.Builder()
                .withId(IdGen.get())
                .withClassId(classId)
                .withStudentId(studentId)
                .build();

        this.persistence.insert(academicPeriod);
        String message = String.format(
                ">>> ENROLL: Student with id %s was enrolled to class with id %s",
                academicPeriod.getStudentId(),
                academicPeriod.getClassId()
        );
        this.logger.info(message);
    }

    public void unlink(String classId, String studentId) {
        AcademicPeriod academicPeriod = new AcademicPeriod.Builder()
                .withClassId(classId)
                .withStudentId(studentId)
                .build();
        List<Document> documents = this.persistence.find(academicPeriod);
        if (documents.isEmpty()) {
            String message = String.format(
                    ">>> DISENROLL: Student with id %s or class with id %s not found",
                    academicPeriod.getStudentId(),
                    academicPeriod.getClassId()
            );
            this.logger.info(message);

            return;
        }
        Document document = documents.get(0);
        academicPeriod.fromDocument(document);
        Boolean result = this.persistence.delete(academicPeriod.getId());
        String message = String.format(
                ">>> DISENROLL: Student with id %s take class with id %s off",
                academicPeriod.getStudentId(),
                academicPeriod.getClassId()
        );
        this.logger.info(message);
    }

    public List<AcademicPeriod> getAllStudentsByClassId(String classId) {
        AcademicPeriod academicPeriod = new AcademicPeriod.Builder()
                .withClassId(classId)
                .withStudentId(null)
                .build();

        List<AcademicPeriod> out = this.getContentByCriteria(academicPeriod); //new ArrayList<>();

        this.logger.info(">>> GET: " + out.size() + " enrolled students in class with id " + classId);

        return out;
    }

    public List<AcademicPeriod> getAllClassesByStudentId(String studentId) {
        AcademicPeriod academicPeriod = new AcademicPeriod.Builder()
                .withClassId(null)
                .withStudentId(studentId)
                .build();

        List<AcademicPeriod> out = this.getContentByCriteria(academicPeriod);

        this.logger.info(">>> GET: " + out.size() + " enrolled classes by student with id " + studentId);

        return out;
    }

    private List<AcademicPeriod> getContentByCriteria(AcademicPeriod academicPeriod) {
        List<Document> list = this.persistence.find(academicPeriod);

        List<AcademicPeriod> out = new ArrayList<>();
        list.forEach(item -> {
            AcademicPeriod enrolledItem = new AcademicPeriod();
            enrolledItem.fromDocument(item);
            out.add(enrolledItem);
        });

        return out;
    }
}
