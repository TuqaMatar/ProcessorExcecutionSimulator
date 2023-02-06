package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Simulator {
    List<Task> tasks;
    private Clock clock;
    private Scheduler schedular;
    private Processor[] processors;
    private int numberOfCycles;


    public Simulator(int numberOfProcessors, int numberOfClockCycles, List<Task> tasks) {
        clock = new Clock();
        schedular = new Scheduler(numberOfProcessors);
        this.tasks = tasks;
        this.numberOfCycles = numberOfClockCycles;
        processors = new Processor[numberOfProcessors];

        //initialize processors
        for (int i = 0; i < numberOfProcessors; i++) {
            Processor processor = new Processor(i+1 , null);
            processors[i] = processor;
        }

    }
    public List<Task> getTasksCreatedAtCurrentTime(int time) {
        List<Task> tasksCreatedAtTime= new ArrayList<>();
        for(Task task :tasks)
        {
            if(task.getCreationTime() == time)
                tasksCreatedAtTime.add(task);
        }
        return tasksCreatedAtTime;

    }
    public void run() throws InterruptedException {
        while (clock.getTime() <= numberOfCycles) {
            System.out.println("At cycle " + clock.getTime() + ":");
            System.out.println("--------------------------------------------------");
            // get tasks created at current time
            List<Task> tasksCreatedAtCurrentTime = getTasksCreatedAtCurrentTime(clock.getTime());
            if (!tasksCreatedAtCurrentTime.isEmpty()) {
                System.out.print("tasks ");
                for(Task task : tasksCreatedAtCurrentTime)
                System.out.print("T"+task.getId()+" ");

                System.out.println((tasksCreatedAtCurrentTime.size() == 1? " is": " are") + " created and added to the queue");
            }
            else {
                System.out.println("No Tasks are created");
            }

            schedular.assignTaskToProcessor(tasksCreatedAtCurrentTime , processors);

            for (int i = 0; i < processors.length; i++) {


                if(processors[i].isTaskFinished()) {
                    System.out.println("task " + processors[i].releaseTask() + " is released");
                    schedular.assignTaskToProcessor(processors);

                }

                Task assignedTask = processors[i].getTask();
                if (assignedTask != null) {
                    System.out.println("processor P" + (i + 1) + " is running " + "T"+ assignedTask.getId() + " remainingTime is :" + assignedTask.getTimeRemainingToFinish());

                } else {
                    System.out.println("processor P" + (i + 1) + " is available");
                }
            }

            System.out.print( schedular.taskQueue.size()==0? "No tasks are waiting. " :"waiting tasks : ");
            schedular.printWaitingTasks();
            System.out.println();

            for(Processor processor : processors)
            {
                if(processor.isBusy())
                 processor.getTask().decrementExecutionTime();
            }

            System.out.println();

            clock.tick();

        }
    }
}
