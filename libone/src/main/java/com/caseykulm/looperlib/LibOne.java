package com.caseykulm.looperlib;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LibOne {

    private static final String TAG = "LibOne";
    private static final int MSG_TASK1 = 1;
    private static final int MSG_TASK2 = 2;
    private static final int MSG_MAIN_SHOW1 = 1;
    private static final int MSG_MAIN_SHOW2 = 2;
    private static LibOne instance;

    private Handler libBgHandler;
    private Handler libMainHandler;

    LibOne() {
        Looper bgLooper = LooperLib.getInstance().getBackgroundLooper();
        this.libBgHandler = new LibBgHandler(bgLooper, this);
        this.libMainHandler = new LibMainThreadHandler(this);
    }

    public static LibOne getInstance() {
        if (instance == null) {
            instance = new LibOne();
        }
        return instance;
    }

    private static class LibBgHandler extends Handler {
        private final LibOne libOne;

        LibBgHandler(Looper looper, LibOne libOne) {
            super(looper);
            this.libOne = libOne;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_TASK1: {
                    String threadName = getLooper().getThread().getName();
                    Log.d(TAG, "task1 run on " + threadName);
                    libOne.show1("important stuff from task1");
                    break;
                }
                case MSG_TASK2: {
                    String threadName = getLooper().getThread().getName();
                    Log.d(TAG, "task2 run on " + threadName);
                    libOne.show2("important stuff from task2");
                    break;
                }
            }
        }
    }

    private static class LibMainThreadHandler extends Handler {
        private final LibOne libOne;

        LibMainThreadHandler(LibOne libOne) {
            this.libOne = libOne;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_MAIN_SHOW1: {
                    String result = (String) msg.obj;
                    String threadName = getLooper().getThread().getName();
                    Log.d(TAG, "received show1 result: " + result + ", on " + threadName);
                    break;
                }
                case MSG_MAIN_SHOW2: {
                    String result = (String) msg.obj;
                    String threadName = getLooper().getThread().getName();
                    Log.d(TAG, "received show2 result: " + result + ", on " + threadName);
                    break;
                }
            }
        }
    }

    public void task1() {
        libBgHandler.sendEmptyMessage(MSG_TASK1);
    }

    public void task2() {
        libBgHandler.sendEmptyMessage(MSG_TASK2);
    }

    public void show1(String input) {
        Message message = libMainHandler.obtainMessage(MSG_MAIN_SHOW1, input);
        libMainHandler.sendMessage(message);
    }

    public void show2(String input) {
        Message message = libMainHandler.obtainMessage(MSG_MAIN_SHOW2, input);
        libMainHandler.sendMessage(message);
    }
}