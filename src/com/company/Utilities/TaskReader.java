package com.company.Utilities;
import com.company.Scheduler.Scheduler;
import com.company.Task.Task;
import com.company.Task.TaskFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TaskReader {

    public static List<Task> getTasksFromFile(String filePath ,Scheduler scheduler ) {
        List<Task> tasks = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                int numberOfLines= Integer.parseInt(br.readLine()) ;
                int count = 1;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" ");
                    int creationTime = Integer.parseInt(parts[0]);
                    int executionTime = Integer.parseInt(parts[1]);
                    int priority = Integer.parseInt(parts[2]);
                    Task task = TaskFactory.createTask(count, creationTime, executionTime, priority);
                    count++;

                    tasks.add(task);
                    scheduler.addTaskToMap(task);
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return tasks;
    }
}
