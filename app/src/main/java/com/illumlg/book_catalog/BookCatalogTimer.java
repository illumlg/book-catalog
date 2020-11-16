package com.illumlg.book_catalog;

import android.os.Handler;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import timber.log.Timber;

public class BookCatalogTimer implements LifecycleObserver {
    private int secondsRunning = 0;
    private int secondsActive = 0;
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
        handler.post(runningTimer);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void startActiveTimer() {
        Timber.i("Active Timer started");
        handler.post(activeTimer);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void stopRunningTimer() {
        handler.removeCallbacks(runningTimer);
        Timber.i("Running Timer stopped");
        Timber.i("Running time: %d/%d seconds", secondsActive, secondsRunning);
        Timber.i("Percent of time app being focused: %.2f%%", secondsActive*100/(float)secondsRunning);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void stopActiveTimer() {
        handler.removeCallbacks(activeTimer);
        Timber.i("Active Timer stopped");
    }
}
