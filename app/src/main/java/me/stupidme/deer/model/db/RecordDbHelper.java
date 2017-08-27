package me.stupidme.deer.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by allen on 17-8-6.
 */

public class RecordDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dear_record.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_RECORD = "record";

    public RecordDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_RECORD + " ("
                + DatabaseColumns.RECORD_ID + " INTEGER PRIMARY KEY NOT NULL, "
                + DatabaseColumns.RECORD_HAS_FINISHED + " INTEGER NOT NULL, "
                + DatabaseColumns.RECORD_START_TIME + " INTEGER NOT NULL, "
                + DatabaseColumns.RECORD_END_TIME + " INTEGER NOT NULL, "
                + DatabaseColumns.RECORD_TIME_LENGTH + " INTEGER NOT NULL, "
                + DatabaseColumns.RECORD_CREATED_TIME + " INTEGER NOT NULL, "
                + DatabaseColumns.RECORD_UPDATED_TIME + " INTEGER NOT NULL, "
                + DatabaseColumns.RECORD_DESCRIBE + " TEXT NOT NULL "
                + ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
