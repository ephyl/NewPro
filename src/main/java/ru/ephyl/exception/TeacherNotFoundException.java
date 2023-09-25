package ru.ephyl.exception;

public class TeacherNotFoundException extends RuntimeException{
    private static final String TEACHER_NOT_FOUND = "Teacher not found";

    @Override
    public String toString() {
        return "TeacherNotFoundException{" +
                "info='" + TEACHER_NOT_FOUND + '\'' +
                '}';
    }
}
