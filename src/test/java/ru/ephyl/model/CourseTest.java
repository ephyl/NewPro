package ru.ephyl.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CourseTest {
    private static Course course;
    private static Course courseWithParams;
    private static Course courseWithParamsTeacher;

    @BeforeAll
    static void beforeAll() {
        course = new Course();
        courseWithParams = new Course(1, "test name");
        courseWithParamsTeacher = new Course("test name", new Teacher("Shifu"));
    }

    @Test
    void setStudents() {
        List<Student> studentList = Mockito.mock(List.class);
        when(studentList.size()).thenReturn(2);
        course.setStudents(studentList);
        courseWithParams.setStudents(studentList);
        assertEquals(2, course.getStudents().size());
        assertEquals(2, courseWithParams.getStudents().size());

    }

    @Test
    void getId() {
        assertEquals(1, courseWithParams.getId());
    }

    @Test
    void setId() {
        course.setId(3);
        assertEquals(3, course.getId());
    }

    @Test
    void getName() {
        assertEquals("test name", courseWithParams.getName());
    }

    @Test
    void setName() {
        assertNull(course.getName());
        String name = "Fine name";
        course.setName(name);
        assertEquals(name, course.getName());
    }

    @Test
    void getTeacher() {
        String teacherFromCourseName = courseWithParamsTeacher.getTeacher().getName();
        Assertions.assertNotNull(teacherFromCourseName);
        assertEquals("Shifu", teacherFromCourseName);
    }

    @Test
    void setTeacher() {
        courseWithParamsTeacher.setTeacher(new Teacher("Splinter"));
        Teacher teacher = courseWithParamsTeacher.getTeacher();
        assertNotNull(teacher);
        assertEquals("Splinter", teacher.getName());
      }
}