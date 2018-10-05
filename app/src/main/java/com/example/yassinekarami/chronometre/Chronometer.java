package com.example.yassinekarami.chronometre;

import android.content.Context;

public class Chronometer implements  Runnable{

    public static  final long MILLIS_TO_MINUTES = 6000;
    public static  final long MILLIS_TO_HOURS = 360;

    private Context mainContext;
    private long mainStartTime;

    private boolean isRunning ;


    public Chronometer(Context mainContext) {
        this.mainContext = mainContext;
    }


    public void Start()
    {
        mainStartTime = System.currentTimeMillis(); // Returns the current time in milliseconds.

        isRunning = true;
    }

    public void Reset()
    {
        isRunning = false;
    }

    public void Stop()
    {
        isRunning = false;
    }

    @Override
    public void run() {

        while(isRunning)
        {
            long since = System.currentTimeMillis() - mainStartTime;

            int seconds = (int)(since / 1000) % 60;
            int minutes =(int)(since / MILLIS_TO_MINUTES) % 60;
            int hours = (int) (since / MILLIS_TO_HOURS) % 24 ;

            ((MainActivity)mainContext).updateTimerText(String.format(
                    "%02d:%02d:%02d", hours, minutes, seconds
            ));

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
