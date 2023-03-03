package com.skyPro.courseWork;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class WeeklyTask extends Task{
    public WeeklyTask(String title, String description, Type type, LocalDateTime localDateTime) throws IncorrectArgumentException {
        super(title,description,type,localDateTime);
    }

    public WeeklyTask(String title, String description, Type type) throws IncorrectArgumentException {
        super(title, description, type);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        if (this.isPastDate(this.getDateTime(),date)) {
            return false;
        }

        return this.getDateTime().toLocalDate().getDayOfWeek()==date.getDayOfWeek();
    }
    @Override
    public String toString() {
        return super.toString()+" {Еженедельная}";
    }
}
