package com.example.yassinekarami.chronometre;

import android.content.Context;

public class Chronometer implements  Runnable{

    public static  final long MILLIS_TO_MINUTES = 60000;
    public static  final long MILLIS_TO_HOURS = 3600;

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
        ((MainActivity)mainContext).updateTimerText(String.format(
                "%02d:%02d:%02d", 0, 0 , 0
        ));

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void Stop()
    {
        isRunning = false;
    }



    @Override
    public void run() {

        while(isRunning)
        {
            long since = System.currentTimeMillis() - mainStartTime; // recupere le temps
            int seconds = (int)(since / 1000) % 60;
            int minutes =(int)((since / MILLIS_TO_MINUTES) % 60);  // 1 minute = 60 000 ms  / MILLIS_TO_MINUTES = 60000

            // TODO : revoir les heures
            int hours = 0;
            // int hours = (int) (since / MILLIS_TO_HOURS) % 24 ;
            int milis = (int)(since % 1000);

            ((MainActivity)mainContext).updateTimerText(String.format(
                    "%02d:%02d:%02d", minutes, seconds , milis
            ));

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
