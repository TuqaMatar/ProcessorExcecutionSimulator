package com.company.Clock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Clock {
    private int tickTime;
    private int tickDuration;
    List<ClockCycle> clockCycles;

    public List<ClockCycle> getClockCycles() {
        return clockCycles;
    }

    public void setClockCycles(List<ClockCycle> clockCycles) {
        this.clockCycles = clockCycles;

    }

    public Clock(int tickDuration , int numOfClockCycles) {
        tickTime = 1 ;
        this.tickDuration = tickDuration;
        clockCycles = new ArrayList<>(numOfClockCycles);
        for(int i = 0 ; i<numOfClockCycles ; i++)
        {
            ClockCycle clockCycle = new ClockCycle();
            clockCycle.setCycleNumber(i+1);
            clockCycles.add(clockCycle);
        }
    }
    public void tick() throws InterruptedException {
        tickTime++;
        TimeUnit.SECONDS.sleep(tickDuration);
    }
    public int getTickTime() {
        return tickTime;
    }
}