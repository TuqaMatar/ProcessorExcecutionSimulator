package com.company;
import java.util.concurrent.TimeUnit;

class Clock {
    private int time;

    public Clock() {
        time = 1;
    }

    public void tick() throws InterruptedException {
        time++;
        TimeUnit.SECONDS.sleep(1);

    }

    public int getTime() {
        return time;
    }
}