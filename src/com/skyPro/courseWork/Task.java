package com.skyPro.courseWork;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Optional;

abstract public class Task implements AppearsInAble{
    /////
    private static int idGenerator = 0;
    private int id;
    private Type type;
    private String title;
    private String description;
    private LocalDateTime dateTime;

    public Task(String title,String description, Type type, LocalDateTime localDateTime) throws IncorrectArgumentException {
        this.id=idGenerator++;

        this.dateTime=LocalDateTime.now();
        if(this.isPastDate(this.dateTime,localDateTime.toLocalDate())) {
            throw new IncorrectArgumentException("Задана дата из прошлого");
        }
        this.dateTime=localDateTime;

        this.setTitle(title);
        this.setDescription(description);

        this.type=type;

    }
    public Task(String title,String description, Type type) throws IncorrectArgumentException {
        this.id=idGenerator++;

        this.dateTime=LocalDateTime.now();

        this.setTitle(title);
        this.setDescription(description);

        this.type=type;
    }
    /////// setters ///////

    public void setTitle(String title) throws IncorrectArgumentException {
        this.title = Optional.ofNullable(title).orElseThrow(()->new IncorrectArgumentException("Заголовок задачи NULL"));
        if (title.isBlank() ) {
            throw new IncorrectArgumentException("Заголовок задачи не заполнен");
        }
        this.title = title;
    }

    public void setDescription(String description) throws  IncorrectArgumentException{

        description=Optional.ofNullable(description).orElseThrow(()->new IncorrectArgumentException("Описание задачи NULL"));
        if (description.isBlank()) {
            throw new IncorrectArgumentException("Описание задачи не заполнено");
        }
        this.description = description;
    }


    /////// getters ///////


    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    /////////////////// abstract method //////////////////

    /**
     * метод проверки задачи по дате
     * @param dateTime
     * @return
     */
    abstract public boolean appearsIn(LocalDate date);

    //////////////////// utility ////////////////////////


    @Override
    public String toString() {
        return "Заметка#"+this.id+" ["+this.getDateTime().toLocalDate()+ " "+this.getDateTime().getHour()+":"+this.getDateTime().getMinute()+":"+this.getDateTime().getSecond()+"] - /"+this.getTitle()+"/ "+this.type+" : "+this.getDescription();
    }

    @Override
    public int hashCode() {
        return this.description.hashCode()+this.title.hashCode()+this.dateTime.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj==null || obj.getClass()!=this.getClass())
            return false;
        Task taskObj = (Task) obj;
        return this.description.equals(taskObj.description) && this.title.equals(taskObj.title) && this.type==taskObj.type && this.dateTime.equals(taskObj.dateTime);
    }
}
