package services;

import models.Student;
import org.bson.Document;
import org.slf4j.Logger;
import utils.Persistence;
import utils.S4Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements GenericRepository<Student> {
    private final Persistence<S4Serializable> persistence;
    private final Logger logger;

    public StudentRepositoryImpl(final Persistence<S4Serializable> persistence, final Logger logger) {
        this.persistence = persistence;
        this.logger = logger;
    }

    @Override
    public Student create(Student student) {
        this.persistence.insert(student);
        this.logger.info(">>> CREATE: Student with id: " + student.getId());

        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Document> list = this.persistence.getAll();
        List<Student> out = new ArrayList<>();
        list.forEach(item -> {
            Student student = new Student();
            student.fromDocument(item);
            out.add(student);
        });

        this.logger.info(">>> GET: Students retrieved: " + out.size());

        return out;
    }

    @Override
    public Optional<Student> getById(String id) {
        Document document = this.persistence.find(id);

        if (document != null) {
            this.logger.info(">>> GET: Student retrieved id: " + id);
        } else {
            this.logger.info(">>> GET: Student with id: " + id + " not found");

            return Optional.empty();
        }
        Student student = new Student();
        student.fromDocument(document);

        return Optional.of(student);
    }

    @Override
    public Boolean delete(String id) {
        Boolean result = this.persistence.delete(id);

        if (result) {
            this.logger.info(">>> DELETE: Student id: " + id);
        } else {
            this.logger.info(">>> DELETE: Student with id: " + id + " not found");
        }

        return result;
    }

    @Override
    public Boolean update(Student student) {
        Boolean result = this.persistence.update(student);

        if (result) {
            this.logger.info(">>> UPDATE: Student updated with id: " + student.getId());
        } else {
            this.logger.info(">>> UPDATE: Student with id: " + student.getId() + " not found");
        }

        return result;
    }
}
