package me.stupidme.deer.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import me.stupidme.deer.model.RecordItem;

/**
 * Created by allen on 17-8-6.
 */

public class RecordManagerImpl implements RecordManager {

    private static WeakReference<Context> mContextRef;

    private static volatile RecordManagerImpl sInstance;

    private SQLiteDatabase mDatabase;

    private RecordManagerImpl() {
        RecordDbHelper helper = new RecordDbHelper(mContextRef.get());
        mDatabase = helper.getWritableDatabase();
    }

    public static void init(Context context) {
        mContextRef = new WeakReference<Context>(context.getApplicationContext());
    }

    public static RecordManagerImpl getInstance() {
        if (mContextRef.get() == null)
            throw new IllegalStateException("RecordManagerImpl has not been initialized yet, context is null.");
        if (sInstance == null)
            synchronized (RecordManagerImpl.class) {
                if (sInstance == null)
                    sInstance = new RecordManagerImpl();
            }
        return sInstance;
    }

    @Override
    public boolean insertRecord(RecordItem item) {
        mDatabase.beginTransaction();
        long id = mDatabase.replace(RecordDbHelper.TABLE_RECORD, null, createContentValues(item));
        if (id == -1) {
            mDatabase.endTransaction();
            return false;
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
        return true;
    }

    @Override
    public boolean deleteRecord(long id) {
        mDatabase.delete(RecordDbHelper.TABLE_RECORD,
                DatabaseColumns.RECORD_ID + "=?",
                new String[]{String.valueOf(id)});
        return true;
    }

    @Override
    public boolean deleteRecord(RecordItem item) {
        return deleteRecord(item.id);
    }

    @Override
    public RecordItem queryRecord(long id) {
        RecordItem item;
        Cursor cursor = mDatabase.query(true, RecordDbHelper.TABLE_RECORD, null,
                "id=?", new String[]{String.valueOf(id)}, null, null, null, null);
        item = createRecordItem(cursor);
        cursor.close();
        return item;
    }

    @Override
    public List<RecordItem> queryRecords() {
        Cursor cursor = mDatabase.query(true, RecordDbHelper.TABLE_RECORD, null,
                null, null, null, null, null, null);
        if (cursor == null)
            return null;
        cursor.moveToFirst();
        List<RecordItem> items = createRecordItems(cursor);
        cursor.close();
        return items;
    }

    @Override
    public boolean updateRecord(RecordItem item) {
        int n = mDatabase.update(RecordDbHelper.TABLE_RECORD, createContentValues(item),
                "id=?", new String[]{String.valueOf(item.id)});
        return n > 0;
    }

    private ContentValues createContentValues(RecordItem item) {
        ContentValues values = new ContentValues(8);
        values.put(DatabaseColumns.RECORD_ID, item.id);
        values.put(DatabaseColumns.RECORD_HAS_FINISHED, item.hasFinished);
        values.put(DatabaseColumns.RECORD_START_TIME, item.startTimeInMills);
        values.put(DatabaseColumns.RECORD_END_TIME, item.endTimeInMillis);
        values.put(DatabaseColumns.RECORD_TIME_LENGTH, item.timeLengthInMills);
        values.put(DatabaseColumns.RECORD_CREATED_TIME, item.createdTime);
        values.put(DatabaseColumns.RECORD_UPDATED_TIME, item.updatedTime);
        values.put(DatabaseColumns.RECORD_DESCRIBE, item.describe);
        return values;
    }

    private RecordItem createRecordItem(Cursor cursor) {
        RecordItem item = new RecordItem();
        item.id = cursor.getLong(cursor.getColumnIndex(DatabaseColumns.RECORD_ID));
        item.hasFinished = cursor.getInt(cursor.getColumnIndex(DatabaseColumns.RECORD_HAS_FINISHED)) != 0;
        item.startTimeInMills = cursor.getLong(cursor.getColumnIndex(DatabaseColumns.RECORD_START_TIME));
        item.endTimeInMillis = cursor.getLong(cursor.getColumnIndex(DatabaseColumns.RECORD_END_TIME));
        item.timeLengthInMills = cursor.getLong(cursor.getColumnIndex(DatabaseColumns.RECORD_TIME_LENGTH));
        item.createdTime = cursor.getLong(cursor.getColumnIndex(DatabaseColumns.RECORD_CREATED_TIME));
        item.updatedTime = cursor.getLong(cursor.getColumnIndex(DatabaseColumns.RECORD_UPDATED_TIME));
        item.describe = cursor.getString(cursor.getColumnIndex(DatabaseColumns.RECORD_DESCRIBE));
        return item;
    }

    private List<RecordItem> createRecordItems(Cursor cursor) {
        List<RecordItem> items = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            items.add(createRecordItem(cursor));
            cursor.moveToNext();
        }
        return items;
    }
}
