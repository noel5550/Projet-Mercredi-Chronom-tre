package com.example.yassinekarami.chronometre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    TextView timer;
    Button btn_start;
    Button btn_reset;
    Button btn_stop;


    private Chronometer chronometer;
    private Thread threadChrono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView)findViewById(R.id.timer);
        btn_reset = (Button)findViewById(R.id.btn_reset);
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_stop = (Button)findViewById(R.id.btn_stop);
    }


    public void StartClick(View view)
    {
        if (chronometer == null)
        {
            chronometer = new Chronometer(this);
            threadChrono = new Thread(chronometer);
            threadChrono.start();
            chronometer.Start();
        }
        else
        {
            chronometer.Resume();
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
            btn_start.setText("Resume");
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
