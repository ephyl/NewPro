package ru.ephyl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ephyl.model.Teacher;

public interface TeacherCrudRepository extends JpaRepository<Teacher, Integer> {
}
