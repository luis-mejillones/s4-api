package services;

import models.Student;

import java.util.Optional;

public interface StudentRepository {
    Student create(Student student);

    Optional<Student> getById(String id);

    Boolean delete(String id);

    Student update(Student student);
}
