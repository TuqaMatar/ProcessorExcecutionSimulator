package com.company.Task;

import java.util.Observable;
import java.util.Observer;

public class Task extends Observable {
    private int id;
    private int creationTime;
    private int executionTime;
    private int priority;
    private int timeRemainingToFinish ;

    public int getCreationTime() {
        return creationTime;
    }

    public Task(int taskId, int creationTime, int executionTime, int priority) {
        this.id = taskId;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
        this.timeRemainingToFinish = executionTime;
    }

    public void decrementRemainingTime() {
        if (timeRemainingToFinish > 0) {
            timeRemainingToFinish--;
        }
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getTimeRemainingToFinish() {
        return timeRemainingToFinish;
    }

    @Override
    public String toString() {
        return "T"+ id;
    }

}
