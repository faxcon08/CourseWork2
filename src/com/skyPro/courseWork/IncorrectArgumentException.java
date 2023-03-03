package com.skyPro.courseWork;

public class IncorrectArgumentException extends Exception{
    private String argument="Default_Argument";


    public IncorrectArgumentException(String argument) {
        super(argument);
        this.argument=argument;
    }

    public IncorrectArgumentException(String argument, Throwable cause) {
        super(argument,cause);
        this.argument=argument;
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "Error: "+argument;
    }
}
