package me.stupidme.deer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.lang.ref.WeakReference;

/**
 * Created by allen on 17-8-6.
 */

public class RecordDatabase {

    private static WeakReference<Context> mContextRef;

    private static volatile RecordDatabase sInstance;

    private SQLiteDatabase mDatabase;

    private RecordDatabase() {
        RecordDbHelper helper = new RecordDbHelper(mContextRef.get());
        mDatabase = helper.getWritableDatabase();
    }

    public static void init(Context context) {
        mContextRef = new WeakReference<Context>(context.getApplicationContext());
    }

    public static RecordDatabase getInstance() {
        if (mContextRef.get() == null)
            throw new IllegalStateException("RecordDatabase has not been initialized yet, context is null.");
        if (sInstance == null)
            synchronized (RecordDatabase.class) {
                if (sInstance == null)
                    sInstance = new RecordDatabase();
            }
        return sInstance;
    }

    public SQLiteDatabase getDatabase() {
        return mDatabase;
    }
}
