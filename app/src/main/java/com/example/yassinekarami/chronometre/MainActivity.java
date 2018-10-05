package com.example.yassinekarami.chronometre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    Button btn_start;
    Button btn_reset;
    Button btn_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
