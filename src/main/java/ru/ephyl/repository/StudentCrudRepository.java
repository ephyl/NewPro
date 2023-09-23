package ru.ephyl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ephyl.model.Student;

import java.util.List;

@Repository
public interface StudentCrudRepository extends JpaRepository<Student, Integer> {
    @Query(
            value = "SELECT * FROM STUDENT s WHERE s.age <31",
            nativeQuery = true)
    List<Student> findAllStudentsUnderThirtyOne();

}


