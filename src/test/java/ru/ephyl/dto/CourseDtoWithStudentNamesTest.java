package ru.ephyl.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseDtoWithStudentNamesTest {
    private static CourseDtoWithStudentNames courseDtoWithStudentNames;

    @BeforeAll
    static void setup() {
        courseDtoWithStudentNames = new CourseDtoWithStudentNames("Course with students names");
        courseDtoWithStudentNames.setStudentNames(List.of("Fry"));
    }

    @Test
    void getName() {
        Assertions.assertEquals("Course with students names", courseDtoWithStudentNames.getName());
    }

    @Test
    void setName() {
        courseDtoWithStudentNames.setName("New Name");
        assertEquals("New Name", courseDtoWithStudentNames.getName());
    }

    @Test
    void getStudentNames() {
        assertEquals(1, courseDtoWithStudentNames.getStudentNames().size());
    }

    @Test
    void setStudentNames() {
        List<String> friendsList = new ArrayList<>();
        friendsList.add("Joe");
        friendsList.add("Chandler");
        courseDtoWithStudentNames.setStudentNames(friendsList);
        assertEquals(2, courseDtoWithStudentNames.getStudentNames().size());
        friendsList.remove(1);
    }
}