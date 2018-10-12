package com.example.yassinekarami.chronometre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private TextView timer;
    private Button btn_start;
    //private Button btn_reset;
    private Button btn_laps;
    private ScrollView scroll;

    private int lap;
    private TextView textLaps;



    private Chronometer chronometer;
    private Thread threadChrono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView)findViewById(R.id.timer);
       // btn_reset = (Button)findViewById(R.id.btn_reset);
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_laps = (Button)findViewById(R.id.btn_laps);
        textLaps = (TextView)findViewById(R.id.laps_text);
        scroll = (ScrollView)findViewById(R.id.scroll);

        textLaps.setEnabled(false);

    }

    public void LapsClick(View view)
    {
        if (chronometer != null)
        {
            textLaps.append("Laps " + String.valueOf(lap)+" : " +String.valueOf(timer.getText()) +" \n");
            lap ++ ;
            scroll.post(new Runnable() {
                @Override
                public void run() {
                    scroll.scrollTo(0,scroll.getBottom());
                }
            });
        }

    }

    public void StartClick(View view)
    {
        if (chronometer == null)
        {
            chronometer = new Chronometer(this);
            threadChrono = new Thread(chronometer);
            threadChrono.start();
            chronometer.Start();

            lap = 1;
            textLaps.setText("");

        }
    }

    public void ResetClick(View view)
    {
        if (chronometer != null )
        {
            chronometer.Reset();
            threadChrono.interrupt();
            threadChrono = null;
            chronometer = null;
            btn_start.setText("Start");
        }


    }


    public void StopClick(View view)
    {
        if (chronometer != null)
        {
            chronometer.Stop();
        }
    }


    public void updateTimerText(final String time)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timer.setText(time);
            }
        });
    }
}
