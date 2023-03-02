package com.skyPro.courseWork;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private Map<Integer,Task> taskMap;
    private Collection<Task> removedTasks;

    public TaskService() {
        taskMap = new HashMap<>();
        removedTasks = new ArrayList<>();
    }

    public void add(Task task) {
        if(task==null) {
            System.out.println("Задача для добавления NULL");
            return;
        }
        taskMap.put(task.getId(),task);
    }

    public Task remove(int id) throws TaskNotFoundException{
        Task removeTask = Optional.ofNullable(this.taskMap.remove(id)).orElseThrow(TaskNotFoundException::new);
        this.removedTasks.add(removeTask);
        return removeTask;
    }

    public Collection<Task> getAllByDate(LocalDate localDate) {
        Collection<Task> taskList = null;
        taskList=this.taskMap.values().stream().filter(x->x.appearsIn(localDate)).collect(Collectors.toList());
        return taskList;
    }

    public void printAllTasksByDate(LocalDate date) {
        Collection<Task> list = this.getAllByDate(date);
        if(list==null) {
            System.out.println("НЕТ задачь для дня: " + date);
            return;
        }
        System.out.println("Задачи для дня: "+date);
        for (Task task : list) {

        }
    }
}
