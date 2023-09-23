package ru.ephyl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ephyl.model.Teacher;
@Repository
public interface TeacherCrudRepository extends JpaRepository<Teacher, Integer> {
}
