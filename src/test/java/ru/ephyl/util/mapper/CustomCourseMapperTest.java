package ru.ephyl.util.mapper;

import org.junit.jupiter.api.Test;
import ru.ephyl.dto.CourseDtoWithStudentNames;
import ru.ephyl.model.Course;
import ru.ephyl.model.Gender;
import ru.ephyl.model.Student;
import ru.ephyl.model.Teacher;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomCourseMapperTest {
    List<Student> students =  new ArrayList<>();
    Student studentMike =  new Student("Mike", 30, Gender.MALE);
    Student studentSasha =  new Student("Sasha", 35, Gender.MALE);
    Course course =  new Course("baba", new Teacher());
    @Test
    void checkStudents(){
        students.add(studentMike);
        students.add(studentSasha);
        course.setStudents(students);
        CourseDtoWithStudentNames courseDtoWithStudentNames = CustomCourseMapper.INSTANCE.customCourseMapper(course);
        System.out.println(courseDtoWithStudentNames.getStudentNames());
        assertEquals(2, courseDtoWithStudentNames.getStudentNames().size(), 0 );
    }

}