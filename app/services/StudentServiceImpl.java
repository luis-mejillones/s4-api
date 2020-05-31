package services;

import com.fasterxml.jackson.databind.JsonNode;
import models.ResponseMessage;
import models.Student;
import org.slf4j.Logger;
import play.libs.Json;

import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    private final Logger logger;
    private final StudentRepository repository;

    public StudentServiceImpl(
            final StudentRepository repository,
            final Logger logger
    ) {
        this.repository = repository;
        this.logger = logger;
    }

    @Override
    public Student create(Student student) {
        return this.repository.create(student);
    }

    @Override
    public Optional<Student> getById(String id) {
        return this.repository.getById(id);
    }

    @Override
    public Boolean delete(String id) {
        return this.repository.delete(id);
    }

    @Override
    public void updatePatch(String id, Student newData) {
        Optional<Student> currentStudent = this.getById(id);
        if (currentStudent.isPresent()) {
            Student current = currentStudent.get();
            current.update(newData);

            this.repository.update(current);
        }
    }

    @Override
    public JsonNode getResponseMessage(Integer status, String body) {
        ResponseMessage response = new ResponseMessage.Builder()
                .withStatus(status)
                .withBody(body)
                .build();

        return Json.toJson(response);
    }
}
