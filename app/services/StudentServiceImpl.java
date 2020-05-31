package services;

import com.fasterxml.jackson.databind.JsonNode;
import models.ResponseMessage;
import models.Student;
import org.slf4j.Logger;
import play.libs.Json;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements GenericService<Student> {
    private final GenericRepository<Student> repository;
    private final Logger logger;

    public StudentServiceImpl(
            final GenericRepository<Student> repository,
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
    public List<Student> getAll() {
        return this.repository.getAll();
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
    public void update(String id, Student newData) {
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
