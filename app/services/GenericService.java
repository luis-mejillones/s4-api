package services;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {
    T create(T student);

    List<T> getAll();

    Optional<T> getById(String id);

    Boolean delete(String id);

    void update(String id, T newData);

    JsonNode getResponseMessage(Integer status, String body);
}
