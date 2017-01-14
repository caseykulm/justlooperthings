package com.caseykulm.justlooperthings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.caseykulm.looperlib.LibOne;
import com.caseykulm.looperlib.LibTwo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doIt(new Runnable() {
            @Override
            public void run() {
                LibOne.getInstance().task1();
            }
        }, 1000);
        doIt(new Runnable() {
            @Override
            public void run() {
                LibOne.getInstance().task2();
            }
        }, 2000);
        doIt(new Runnable() {
            @Override
            public void run() {
                LibTwo.getInstance().task1();
            }
        }, 3000);
        doIt(new Runnable() {
            @Override
            public void run() {
                LibTwo.getInstance().task2();
            }
        }, 4000);
    }

    private void doIt(Runnable runnable, long delay) {
        TextView textView = (TextView) findViewById(R.id.text);
        textView.postDelayed(runnable, delay);
    }
}
