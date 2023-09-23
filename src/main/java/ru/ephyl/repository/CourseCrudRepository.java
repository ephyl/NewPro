package ru.ephyl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ephyl.model.Course;

import java.util.List;

@Repository
public interface CourseCrudRepository extends JpaRepository<Course, Integer> {
@Query(name = "allUniversityWithStudents", value = "SELECT c FROM Course c LEFT JOIN FETCH c.students")
List<Course> getAllCoursesWithStudents();
}