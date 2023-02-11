package com.company.Utilities;
import com.company.Clock.ClockCycle;
import com.company.Processor.Processor;
import com.company.Task.Task;
import java.util.List;

public class ReportWriter {
    public static void PrintClockCycleData(ClockCycle clockCycle) {
        System.out.println("At cycle " + clockCycle.getCycleNumber() + ":");
        System.out.println("--------------------------------------------------");
        printCreatedTasks(clockCycle);
        printRunningProcessors(clockCycle);
        printAvailableProcessors(clockCycle);
        printReleasedTasks(clockCycle);
        printWaitingTasks(clockCycle);
        System.out.println();
    }

    private static void printAvailableProcessors(ClockCycle clockCycle) {
        List<Processor> availableProcessors = clockCycle.getAvailableProcessors();
        if(!availableProcessors.isEmpty())
        {
            System.out.print(availableProcessors.size() == 1 ?"processor ":"processors ");
            System.out.print(availableProcessors);
            System.out.println((availableProcessors.size() == 1 ? " is" : " are") + " available ");
        }
    }

    private static void printRunningProcessors(ClockCycle clockCycle) {
        List<Processor> runningProcessors = clockCycle.getRunningProcessors();
        for (Processor processor : runningProcessors)
            System.out.println("processor " + processor +
                    " is running " + processor.getTask() +
                    " remainingTime is :" +
                    processor.getTask().getTimeRemainingToFinish());
    }

    private static void printReleasedTasks(ClockCycle clockCycle) {
        //     System.out.println("task " + task + " is released");
        if (clockCycle.getReleasedTasks().isEmpty()) {
            System.out.println("No Tasks were released");
        } else {
            List<Task> releasedTasks = clockCycle.getReleasedTasks();
            System.out.print(releasedTasks.size() == 1 ? "task " : "tasks ");
            System.out.print(releasedTasks);
            System.out.println((releasedTasks.size() == 1 ? " was" : " were") + " released ");
        }
    }

    private static void printWaitingTasks(ClockCycle clockCycle) {
        if (clockCycle.getWaitingTasks().size() == 0) {
            System.out.println("No tasks are waiting. ");
        } else {
            System.out.print("waiting tasks : ");
            System.out.println(clockCycle.getWaitingTasks());

        }
    }

    public static void printCreatedTasks(ClockCycle clockCycle) {
        if (clockCycle.getCreatedTasks().isEmpty()) {
            System.out.println("No Tasks are created");
        } else {
            List<Task> createdTasks = clockCycle.getCreatedTasks();
            System.out.print("tasks ");
            System.out.print(createdTasks);
            System.out.println((createdTasks.size() == 1 ? " is" : " are") + " created and added to the queue");
        }
    }

}
