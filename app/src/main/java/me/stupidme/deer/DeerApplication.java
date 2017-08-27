package me.stupidme.deer;

import android.app.Application;

import me.stupidme.deer.model.db.RecordManagerImpl;

/**
 * Created by allen on 17-8-6.
 */

public class DeerApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        RecordManagerImpl.init(this);
    }
}
