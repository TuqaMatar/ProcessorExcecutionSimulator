package com.company;
public class Processor {
    public Task getTask() {
        return task;
    }

    private Task task;
    private int id;

    public Processor(int id) {
        this.id = id;
    }
    public Processor(int id , Task task) {
        this.id = id;
        this.task = task;
    }

    public Processor() {

    }

    public void assignTask(Task task) {
        this.task = task;
    }

    public boolean isBusy() {
        return task != null;
    }

    public void update() {
        if (isBusy()) {
            task.decrementExecutionTime();
        }
    }

    public boolean isTaskFinished() {
        return isBusy() && task.getTimeRemainingToFinish() == 0;
    }

    public Task releaseTask() {
        Task releasedTask = task;
        task = null;
        return releasedTask;
    }

    public void removeTask() {
        assignTask(null);
    }

}
