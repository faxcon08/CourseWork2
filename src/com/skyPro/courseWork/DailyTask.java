package com.skyPro.courseWork;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(String title, String description, Type type, LocalDateTime localDateTime) throws IncorrectArgumentException {
        super(title, description, type,localDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        if (this.isPastDate(this.getDateTime(),date))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return super.toString()+" {Daily}";
    }
}
