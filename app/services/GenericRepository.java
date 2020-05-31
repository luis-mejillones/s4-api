package services;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {
    T create(T student);

    List<T> getAll();

    Optional<T> getById(String id);

    Boolean delete(String id);

    Boolean update(T student);
}
