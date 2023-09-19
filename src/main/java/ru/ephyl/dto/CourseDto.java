package ru.ephyl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDto {

    @Size(min =2, max = 50, message = "Size should be between 2 an 50")
    private String name;
    private List<StudentDto> studentDtoList;

    public CourseDto(String name) {
        this.name = name;
    }

    public CourseDto() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentDto> getStudentDtoList() {
        return studentDtoList;
    }

    public void setStudentDtoList(List<StudentDto> studentDtoList) {
        this.studentDtoList = studentDtoList;
    }
}
