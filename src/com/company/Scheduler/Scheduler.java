package com.company.Scheduler;

import com.company.Processor.Processor;
import com.company.Task.Task;

import java.util.List;
import java.util.Observer;
import java.util.PriorityQueue;

public interface Scheduler extends Observer {
     void assignTaskToProcessor(List<Processor> processors);
     void addTasksToQueue(List<Task> tasks);
     void addTaskToMap(Task task);
     List<Task> getWaitingTasks();
     List<Task> getTasksCreatedAtCurrentTime(int currentTime);
     PriorityQueue<Task> getTaskQueue();
}
