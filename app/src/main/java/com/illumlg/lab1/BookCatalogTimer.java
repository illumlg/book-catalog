package com.illumlg.lab1;

import android.os.Handler;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import timber.log.Timber;

public class BookCatalogTimer implements LifecycleObserver {
    private int secondsRunning = 1;
    private int secondsActive = 1;
    private Handler handler = new Handler();
    private Runnable runningTimer = new Runnable() {
        @Override
        public void run() {
            //Timber.i("Running Timer is at %s", secondsRunning);
            secondsRunning++;
            handler.postDelayed(this, 1000);
        }
    };
    private Runnable activeTimer = new Runnable() {
        @Override
        public void run() {
            Timber.i("Active Timer is at %s", secondsActive);
            secondsActive++;
            handler.postDelayed(this, 1000);
        }
    };

    public BookCatalogTimer(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void startRunningTimer() {
        Timber.i("Running Timer started");
        handler.postDelayed(runningTimer, 1000);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void startActiveTimer() {
        Timber.i("Active Timer started");
        handler.postDelayed(activeTimer, 1000);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void stopRunningTimer() {
        handler.removeCallbacks(runningTimer);
        Timber.i("Running Timer stopped");
        Timber.i("Active time: %d seconds", secondsActive);
        Timber.i("Total running time: %d seconds", secondsRunning);
        Timber.i("Percent of time app being focused: %.2f%%", secondsActive*100/(float)secondsRunning);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void stopActiveTimer() {
        handler.removeCallbacks(activeTimer);
        Timber.i("Active Timer stopped");
    }
}
