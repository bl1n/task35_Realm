package com.lft.task35;

import android.app.Application;

import io.realm.Realm;

public class AppDelegate extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
