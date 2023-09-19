package ru.ephyl.exception;

public class TeacherNotFoundException extends RuntimeException{
    private final String info = "Teacher not found";
    public TeacherNotFoundException() {

    }

    @Override
    public String toString() {
        return "TeacherNotFoundException{" +
                "info='" + info + '\'' +
                '}';
    }
}
