package com.caseykulm.looperlib;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LibTwo {

    private static final String TAG = "LibTwo";
    private static final int MSG_TASK1 = 1;
    private static final int MSG_TASK2 = 2;
    private static LibTwo instance;

    private Handler libHandler;

    LibTwo() {
        Looper bgLooper = LooperLib.getInstance().getBackgroundLooper();
        this.libHandler = new LibBgHandler(bgLooper);
    }

    public static LibTwo getInstance() {
        if (instance == null) {
            instance = new LibTwo();
        }
        return instance;
    }

    private static class LibBgHandler extends Handler {
        LibBgHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_TASK1: {
                    String threadName = getLooper().getThread().getName();
                    Log.d(TAG, "task1 run on " + threadName);
                    break;
                }
                case MSG_TASK2: {
                    String threadName = getLooper().getThread().getName();
                    Log.d(TAG, "task2 run on " + threadName);
                    break;
                }
            }
        }
    }

    public void task1() {
        libHandler.sendEmptyMessage(MSG_TASK1);
    }

    public void task2() {
        libHandler.sendEmptyMessage(MSG_TASK2);
    }
}
