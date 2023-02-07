package com.company;

import java.util.*;

import java.util.ArrayList;

public class Scheduler {
    List<Task> tasks;
    PriorityQueue<Task> taskQueue;
    private Map<Integer, List<Task>> taskMap = new HashMap<>();
    private Processor[] processors;
    private int numProcessors;

    public Scheduler(int numProcessors) {
        this.numProcessors = numProcessors;
        processors = new Processor[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            processors[i] = new Processor();
        }
        taskQueue = new PriorityQueue<>(new TaskPriorityComparator());
    }
    private void addTaskToMap(Task task) {
        int creationTime = task.getCreationTime();
        if (!taskMap.containsKey(creationTime)) {
            taskMap.put(creationTime, new ArrayList<Task>());
        }
        taskMap.get(creationTime).add(task);
    }

//    public List<Task> getTasksCreatedAtCurrentTime(int time) {
//        List<Task> tasksCreatedAtTime = new ArrayList<>();
//        for (Task task : tasks) {
//            if (task.getCreationTime() == time)
//                tasksCreatedAtTime.add(task);
//        }
//        return tasksCreatedAtTime;
//
//    }

    private List<Task> getTasksCreatedAtCurrentTime(int currentTime) {
        if (!taskMap.containsKey(currentTime)) {
            return new ArrayList<Task>();
        }
        return taskMap.get(currentTime);
    }

    public void addTaskstoQueue(List<Task> tasksCreatedAtCurrentTime) {
        for (Task task : tasksCreatedAtCurrentTime) {
            taskQueue.add(task);
            addTaskToMap(task);
        }
    }

    public void printWaitingTasks() {
        Iterator<Task> it = taskQueue.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            System.out.print (task.toString()+" ");
        }
    }

    public void assignTaskToProcessor(List<Task> tasksCreatedAtCurrentTime, Processor[] processors) {
        addTaskstoQueue(tasksCreatedAtCurrentTime);
        for(Processor processor : processors)
            if(!processor.isBusy())
                processor.assignTask(taskQueue.poll());
    }

    public void assignTaskToProcessor(Processor[] processors)
    {
        for(Processor processor : processors)
            if(!processor.isBusy())
                processor.assignTask(taskQueue.poll());
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

