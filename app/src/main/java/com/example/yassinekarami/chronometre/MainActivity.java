package com.example.yassinekarami.chronometre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private Button btn_start;
    private Button btn_stop;
    private Button btn_reset;
    private Button btn_laps;


    private ScrollView scroll;

    private int lap;
    private TextView textLaps;
    private long stop;



    private Chronometer chronometer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = (Chronometer)findViewById(R.id.chrono);

        btn_start = (Button)findViewById(R.id.btn_start);

        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_reset = (Button)findViewById(R.id.btn_reset);

        btn_laps = (Button)findViewById(R.id.btn_laps);
        textLaps = (TextView)findViewById(R.id.laps_text);

        scroll = (ScrollView)findViewById(R.id.scroll);

        btn_reset.setVisibility(View.GONE);
    }

    public void StartClick(View view)
    {
        if (stop != 0)
        {
            chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - stop);
        }
        else
        {
            chronometer.setBase(SystemClock.elapsedRealtime());
        }
        chronometer.start();
        lap = 1;

        btn_reset.setVisibility(View.GONE);
        btn_stop.setVisibility(View.VISIBLE);
        btn_start.setEnabled(false);

    }

    public void StopClick(View view)
    {
        stop = SystemClock.elapsedRealtime();
        chronometer.stop();
        btn_reset.setVisibility(View.VISIBLE);
        btn_stop.setVisibility(View.GONE);
        btn_start.setEnabled(true);
    }

    public void ResetClick(View view)
    {

        textLaps.setText("\n");
        chronometer.setBase(SystemClock.elapsedRealtime());
        stop = 0;
        btn_start.setEnabled(true);
    }


    public void LapsClick(View view)
    {
        if (chronometer != null)
        {
            textLaps.append("Laps " + String.valueOf(lap)+" : " +String.valueOf(chronometer.getText()) +" \n");
            lap ++ ;
            scroll.post(new Runnable() {
                @Override
                public void run() {
                    scroll.scrollTo(0,scroll.getBottom());
                }
            });
        }

    }

}
