package ru.ephyl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.ephyl.model.Gender;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {
    private int id;
    @NotEmpty(message = "Should not be Empty")
    @Size(min =2, max = 50, message = "Size should be between 2 an 50")
    private String name;
    @Min(value = 0,message = "age should be greater that 0" )
    private int age;

    @NotEmpty(message = "Should not be Empty")
    private Gender gender;

    private List<CourseDto> coursesDtoList;

    public List<CourseDto> getCoursesDtoList() {
        return coursesDtoList;
    }

    public void setCoursesDtoList(List<CourseDto> coursesDtoList) {
        this.coursesDtoList = coursesDtoList;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public StudentDto(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public StudentDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
