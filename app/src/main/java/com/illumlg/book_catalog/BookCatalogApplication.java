package com.illumlg.book_catalog;

import android.app.Application;

import timber.log.Timber;

public class BookCatalogApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
