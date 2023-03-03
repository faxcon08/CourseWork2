package com.skyPro.courseWork;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task{
    public OneTimeTask(String title, String description, Type type, LocalDateTime localDateTime) throws IncorrectArgumentException {
        super(title, description, type,localDateTime);
    }

    public OneTimeTask(String title, String description, Type type) throws IncorrectArgumentException {
        super(title, description, type);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        if(this.isPastDate(this.getDateTime(),date))
            return false;
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return taskDate.getYear()==date.getYear() && taskDate.getMonth()==date.getMonth() && taskDate.getDayOfMonth()==date.getDayOfMonth();
    }
    @Override
    public String toString() {
        return super.toString()+" {Одноразовая}";
    }
}
