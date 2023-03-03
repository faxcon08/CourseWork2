package com.skyPro.courseWork;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AppearsInAble {
    abstract public boolean appearsIn(LocalDate date);

    default  boolean isPastDate(LocalDateTime taskDateTime, LocalDate date){/// 02-03-23 & 01-01-24
        LocalDate taskDate= taskDateTime.toLocalDate();

        return date.isBefore(taskDate) && !date.isEqual(taskDate);
    }
}
