package me.stupidme.deer;

import android.app.Application;

import me.stupidme.deer.db.RecordDatabase;

/**
 * Created by allen on 17-8-6.
 */

public class DeerApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        RecordDatabase.init(this);
    }
}
