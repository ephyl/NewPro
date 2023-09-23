package ru.ephyl.dto;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseDtoTest {

    @Test
    void getName() {
        CourseDto courseDto = new CourseDto("New course");
        assertEquals("New course", courseDto.getName());

    }

    @Test
    void setName() {
        CourseDto courseDto = new CourseDto();
        courseDto.setName("N");
        assertEquals("N", courseDto.getName());
    }

    @Test
    void setToCourseStudentDtoListTest() {
        CourseDto courseDto = new CourseDto();
        StudentDto studentDto = new StudentDto();
        studentDto.setName("Studentas");
        courseDto.setStudentDtoList(List.of(studentDto));
        assertEquals(1, courseDto.getStudentDtoList().size());
        assertEquals("Studentas", courseDto.getStudentDtoList().get(0).getName());
    }

}