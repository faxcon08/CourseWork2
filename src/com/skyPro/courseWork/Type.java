package com.skyPro.courseWork;

import java.util.Optional;

public enum Type {
    WORK("Рабочая"),
    PERSONAL("Личная");
    String name;
    Type(String name) {
        this.name=name;
    }


    @Override
    public String toString() {
        return " <"+name+"> ";
    }
}
