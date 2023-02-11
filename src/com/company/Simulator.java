package com.company;

import com.company.Clock.Clock;
import com.company.Clock.ClockCycle;
import com.company.Processor.Processor;
import com.company.Scheduler.Scheduler;
import com.company.Task.Task;
import com.company.Utilities.ReportWriter;

import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private Clock clock;
    private Scheduler scheduler;
    private List<Processor> processors;
    List<Task> tasks;
    private int numberOfCycles;

    public Simulator(int numberOfProcessors, int numberOfClockCycles, List<Task> tasks , Scheduler scheduler) {
        clock = new Clock(1, numberOfClockCycles);
        this.scheduler =scheduler;
        this.tasks = tasks;
        this.numberOfCycles = numberOfClockCycles;
        processors = new ArrayList<>(numberOfProcessors);

        //initialize processors
        for (int i = 0; i < numberOfProcessors; i++) {
            Processor processor = new Processor(i + 1, null);
            processor.addObserver(scheduler);
            processors.add(processor);

        }
    }

    public void run() throws InterruptedException {
        List<ClockCycle> clockCycles = clock.getClockCycles();
        List<Task> finishedTasks = new ArrayList<>();
        List<Processor> runningProcessors = new ArrayList<>();
        List<Processor> availableProcessors = new ArrayList<>();

        for (int i = 0; i < numberOfCycles; i++) {
            finishedTasks.clear();
            runningProcessors.clear();
            availableProcessors.clear();
            ClockCycle currentClockCycle = clockCycles.get(i);

            List<Task> tasksCreatedAtCurrentTime = scheduler.getTasksCreatedAtCurrentTime(clock.getTickTime());

            currentClockCycle.setCreatedTasks(tasksCreatedAtCurrentTime);

            scheduler.addTasksToQueue(tasksCreatedAtCurrentTime);
            scheduler.assignTaskToProcessor(processors);

            for (int j = 0; j < processors.size(); j++) {
                Task assignedTask = processors.get(j).getTask();
                if (processors.get(j).isTaskFinished()) {
                    Task task = processors.get(j).releaseTask();
                    finishedTasks.add(task);
                }
                if (assignedTask != null) {
                    runningProcessors.add(processors.get(j));
                } else {
                    availableProcessors.add(processors.get(j));
                }
            }

            currentClockCycle.setRunningProcessors(runningProcessors);
            currentClockCycle.setAvailableProcessors(availableProcessors);
            currentClockCycle.setReleasedTasks(finishedTasks);

            if (scheduler.getTaskQueue().size() == 0) {
                currentClockCycle.setWaitingTasks(new ArrayList<>());
            } else {
                currentClockCycle.setWaitingTasks(scheduler.getWaitingTasks());
            }

            ReportWriter.PrintClockCycleData(currentClockCycle);

            for (Processor processor : processors) {
                if (processor.isBusy())
                    processor.getTask().decrementRemainingTime();
            }

            System.out.println();
            clock.tick();
        }

    }
}
