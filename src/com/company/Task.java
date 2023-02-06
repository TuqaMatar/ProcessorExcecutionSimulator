package com.company;

public class Task {
    private int creationTime;
    private int executionTime;
    private int priority;
    private boolean isRunning;
    private int timeRemainingToFinish ;
    private int completionTime;

    public static final int HIGH_PRIORITY = 1;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getCreationTime() {
        return creationTime;
    }



    public Task(String taskId, int creationTime, int executionTime, int priority) {
        this.id = taskId;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
        this.timeRemainingToFinish = executionTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void decrementExecutionTime() {
        if (timeRemainingToFinish > 0) {
            timeRemainingToFinish--;
        }
    }

    boolean isAssigned(){
        return isRunning;
    }

    @Override
    public String toString() {
        return "T"+ id;
    }

    public int getTimeRemainingToFinish() {
        return timeRemainingToFinish;
    }

    public void setTimeRemainingToFinish(int timeRemainingToFinish) {
        this.timeRemainingToFinish = timeRemainingToFinish;
    }
}
