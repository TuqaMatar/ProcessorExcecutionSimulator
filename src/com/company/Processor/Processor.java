package com.company.Processor;

import com.company.Task.Task;

import java.util.Observable;

public class Processor extends Observable {
    private Task task;
    private int id;

    public Processor(int id , Task task) {
        this.id = id;
        this.task = task;
    }

    public void assignTask(Task task) {
        this.task = task;
    }

    public boolean isBusy() {
        return task != null;
    }

    public boolean isTaskFinished() {
        return  task.getTimeRemainingToFinish() == 0;
    }

    public Task releaseTask() {
        Task releasedTask = task;
        task = null;
        setChanged();
        notifyObservers();
        return releasedTask;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public String toString() {
        return "P"+id;
    }
}
