package com.skyPro.courseWork;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AppearsInAble {
    abstract public boolean appearsIn(LocalDate date);

    default  boolean isPastDate(LocalDateTime taskDateTime, LocalDate date){/// 02-03-23 & 01-01-24
        LocalDate taskDate= taskDateTime.toLocalDate();
        if (taskDate.getYear() <= date.getYear()) { //23 23
            if (taskDate.getYear() == date.getYear()) {
                if (taskDate.getMonthValue() <= date.getMonthValue()) {
                    if (taskDate.getMonthValue() == date.getMonthValue()) {
                        if (taskDate.getDayOfMonth() <= date.getDayOfMonth()) {
                            return false;
                        }else return true;
                    } else return false;
                }else return true;
            }else return false;
        }
        return true;
    }
}
