package com.company.Task;

public class TaskFactory {
    public static Task createTask(int id, int creationTime, int executionTime, int priority) {
        Task task = new Task(id, creationTime, executionTime, priority);
        return task;
    }
}