package ru.ephyl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDtoWithStudentNames {

    @Size(min =2, max = 50, message = "Size should be between 2 an 50")
    private String name;
    private List<String> studentNames;

    public CourseDtoWithStudentNames(String name) {
        this.name = name;
    }

    public CourseDtoWithStudentNames() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(List<String> studentNames) {
        this.studentNames = studentNames;
    }
}
