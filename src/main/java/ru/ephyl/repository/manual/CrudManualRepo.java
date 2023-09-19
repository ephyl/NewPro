package ru.ephyl.repository.manual;

import java.util.List;
import java.util.Optional;

public interface CrudManualRepo <T>{
    List<T> getAll();
    Optional<T> findById(int id);
    long addNew(T object);
    void delete(int id);
    boolean update(T object);
}
