package com.company;

class TaskFactory {
    public static Task createTask(String id, int creationTime, int executionTime, int priority) {
        Task task = new Task(id, creationTime,priority, executionTime);
        return task;
    }
}