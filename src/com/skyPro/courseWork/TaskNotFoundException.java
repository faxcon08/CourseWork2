package com.skyPro.courseWork;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException() {
        super("Задача не была найдена для удаления по ID");
    }
}
