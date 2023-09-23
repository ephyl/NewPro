package ru.ephyl.exception;

public class StudentNotFoundException extends RuntimeException {

    private static final String STUDENT_NOT_FOUND = "Student not found";
    @Override
    public String toString() {
        return "StudentNotFoundException{" +
                STUDENT_NOT_FOUND + '}';
    }
}
