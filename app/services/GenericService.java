package services;

import com.fasterxml.jackson.databind.JsonNode;
import models.Student;

import java.util.Optional;

public interface GenericService<T> {
    T create(T student);

    Optional<T> getById(String id);

    Boolean delete(String id);

    void update(String id, Student newData);

    JsonNode getResponseMessage(Integer status, String body);
}
