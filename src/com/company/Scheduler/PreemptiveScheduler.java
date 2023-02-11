package com.company.Scheduler;

import com.company.Processor.Processor;
import com.company.Task.Task;

import java.util.*;

import java.util.ArrayList;

public class PreemptiveScheduler implements Scheduler {
    PriorityQueue<Task> taskQueue;
    private Map<Integer, List<Task>> taskMap = new HashMap<>();

    public PreemptiveScheduler() {
        taskQueue = new PriorityQueue<>(new TaskPriorityComparator());
    }

    @Override
    public PriorityQueue<Task> getTaskQueue() {
        return taskQueue;
    }
    @Override
    public void addTasksToQueue(List<Task> tasksCreatedAtCurrentTime) {
        for (Task task : tasksCreatedAtCurrentTime) {
            taskQueue.add(task);
        }
    }

    @Override
    public List<Task> getWaitingTasks() {
        List<Task> waitingTasks = new ArrayList<>();
        Iterator<Task> it = taskQueue.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            waitingTasks.add(task);
        }
        return waitingTasks;
    }


    @Override
    public void addTaskToMap(Task task) {
        int creationTime = task.getCreationTime();
        if (!taskMap.containsKey(creationTime)) {
            taskMap.put(creationTime, new ArrayList<Task>());
        }
        taskMap.get(creationTime).add(task);
    }

    @Override
    public void assignTaskToProcessor(List<Processor> processors) {
        for (Processor processor : processors)
            if (!processor.isBusy())
                processor.assignTask(taskQueue.poll());
    }

    @Override
    public List<Task> getTasksCreatedAtCurrentTime(int currentTime) {
        if (!taskMap.containsKey(currentTime)) {
            return new ArrayList<Task>();
        }
        return taskMap.get(currentTime);
    }

    @Override
    public void update(Observable observable, Object arg) {
        // Check if the state of the Processor has changed, and if so, assign a new task to it
        if (observable instanceof Processor) {
            Processor processor = (Processor) observable;
            processor.assignTask(taskQueue.poll());
        }
    }

    class TaskPriorityComparator implements Comparator<Task> {
        @Override
        public int compare(Task t1, Task t2) {
            int result = Integer.compare(t2.getPriority(), t1.getPriority());
            if (result == 0) {
                result = Integer.compare(t2.getExecutionTime(), t1.getExecutionTime());
            }
            return result;
        }
    }
}

