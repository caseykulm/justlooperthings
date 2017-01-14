package com.caseykulm.justlooperthings;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.caseykulm.looperlib.LibOne;
import com.caseykulm.looperlib.LibTwo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler uiHandler = new Handler();
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LibOne.getInstance().task1();
            }
        }, 1000);
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LibOne.getInstance().task2();
            }
        }, 2000);
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LibTwo.getInstance().task1();
            }
        }, 3000);
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LibTwo.getInstance().task2();
            }
        }, 4000);
    }

}
