package services;

import models.Student;

import java.util.Optional;

public interface GenericRepository<T> {
    T create(T student);

    Optional<T> getById(String id);

    Boolean delete(String id);

    Boolean update(Student student);
}
