package com.company;

import com.company.Scheduler.PreemptiveScheduler;
import com.company.Scheduler.Scheduler;
import com.company.Task.Task;
import com.company.Utilities.TaskReader;

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

        Scheduler scheduler = new PreemptiveScheduler();
        List<Task> tasks = TaskReader.getTasksFromFile(taskInformationFile , scheduler);
        Simulator simulator = new Simulator(numberOfProcessors , numberOfClockCycles , tasks , scheduler);
        simulator.run();
    }
}
