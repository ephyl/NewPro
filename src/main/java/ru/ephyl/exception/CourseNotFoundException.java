package ru.ephyl.exception;

public class CourseNotFoundException extends RuntimeException {
    private static final String COURSE_NOT_FOUND = "Course not found";
    @Override
    public String toString() {
        return "CourseNotFoundException{" +
                "info='" + COURSE_NOT_FOUND + '\'' +
                '}';
    }
}
