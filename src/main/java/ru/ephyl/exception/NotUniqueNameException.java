package ru.ephyl.exception;

public class NotUniqueNameException  extends RuntimeException{
    private final static String INFO = "Not unique name";

    @Override
    public String toString() {
        return "NotUniqueNameException{" +
                "INFO='" + INFO + '\'' +
                '}';
    }


}
