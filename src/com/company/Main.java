package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if (args.length < 3) {
            System.out.println("Insufficient arguments. Usage: java Simulator <number of processors> <number of clock cycles> <task information file>");
            return;
        }

        int numberOfProcessors = Integer.parseInt(args[0]);
        int numberOfClockCycles = Integer.parseInt(args[1]);
        String taskInformationFile = args[2];


        List<Task> tasks = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(taskInformationFile))) {
                String line;
                int count = 1 ;
                int numberOfLines= Integer.parseInt(br.readLine()) ;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" ");
                    int creationTime = Integer.parseInt(parts[0]);
                    int executionTime = Integer.parseInt(parts[1]);
                    int priority = Integer.parseInt(parts[2]);
                    Task task = TaskFactory.createTask(String.valueOf(count) ,creationTime,executionTime,priority);
                    count++;
                    tasks.add(task);
                }

                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }


        Simulator simulator = new Simulator(numberOfProcessors , numberOfClockCycles , tasks);
        simulator.run();
    }
}
