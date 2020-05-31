package services;

import com.fasterxml.jackson.databind.JsonNode;
import models.Student;

import java.util.Optional;

public interface StudentService {
    Student create(Student student);

    Optional<Student> getById(String id);

    Boolean delete(String id);

    void updatePatch(String id, Student newData);

    JsonNode getResponseMessage(Integer status, String body);
}
