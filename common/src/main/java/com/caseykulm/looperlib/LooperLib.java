package com.caseykulm.looperlib;

import android.os.HandlerThread;
import android.os.Looper;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

class LooperLib {

    private static final String THREAD_PREFIX = "LooperLib-";
    private static final String WORKER_THREAD_NAME = "Worker";

    private static LooperLib instance;

    private BackgroundThread bgThread;

    private LooperLib() {
        bgThread = new BackgroundThread();
        bgThread.start();
    }

    public static LooperLib getInstance() {
        if (instance == null) {
            instance = new LooperLib();
        }
        return instance;
    }

    private static class BackgroundThread extends HandlerThread {
        BackgroundThread() {
            super(THREAD_PREFIX + WORKER_THREAD_NAME, THREAD_PRIORITY_BACKGROUND);
        }
    }

    Looper getBackgroundLooper() {
        return bgThread.getLooper();
    }
}
