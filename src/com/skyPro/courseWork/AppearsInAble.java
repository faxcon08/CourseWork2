package com.skyPro.courseWork;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AppearsInAble {
    abstract public boolean appearsIn(LocalDate date);

    default boolean isPastDate(LocalDateTime taskDateTime, LocalDate date){
        LocalDate taskDate= taskDateTime.toLocalDate();
        if(date.getYear()<taskDate.getYear()){
            return true;
        } else if (date.getMonth().getValue() < taskDate.getMonthValue()) {
            return true;
        } else if (date.getDayOfMonth() < taskDate.getDayOfMonth()) {
            return true;
        }
        return false;
    }
}
