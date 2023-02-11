package com.company.Clock;

import com.company.Processor.Processor;
import com.company.Task.Task;

import java.util.List;

public class ClockCycle {
    int cycleNumber;
    List<Processor> runningProcessors;
    List<Processor> availableProcessors;
    List<Task> createdTasks;
    List<Task> waitingTasks ;
    List<Task> releasedTasks;

    public List<Task> getCreatedTasks() {
        return createdTasks;
    }
    public void setCreatedTasks(List<Task> createdTasks) {
        this.createdTasks = createdTasks;
    }
    public List<Processor> getRunningProcessors() {
        return runningProcessors;
    }
    public int getCycleNumber() {
        return cycleNumber;
    }

    public void setCycleNumber(int cycleNumber) {
        this.cycleNumber = cycleNumber;
    }
    public void setRunningProcessors(List<Processor> runningProcessors) {
        this.runningProcessors = runningProcessors;
    }
    public List<Task> getWaitingTasks() {
        return waitingTasks;
    }

    public void setWaitingTasks(List<Task> waitingTasks) {
        this.waitingTasks = waitingTasks;
    }

    public List<Task> getReleasedTasks() {
        return releasedTasks;
    }

    public void setReleasedTasks(List<Task> releasedTasks) {
        this.releasedTasks = releasedTasks;
    }

    public List<Processor> getAvailableProcessors() {
        return availableProcessors;
    }

    public void setAvailableProcessors(List<Processor> availableProcessors) {
        this.availableProcessors = availableProcessors;
    }
}
