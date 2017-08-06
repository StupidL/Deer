package me.stupidme.deer.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import me.stupidme.deer.db.RecordDatabase;

/**
 * Created by allen on 17-8-6.
 */

public class RecordItemModelImpl implements RecordItemModel {

    private SQLiteDatabase mDatabase;

    public RecordItemModelImpl(){
        mDatabase = RecordDatabase.getInstance().getDatabase();
    }

    @Override
    public void insertItem(RecordItem item) {

    }

    @Override
    public void deleteItem(RecordItem item) {

    }

    @Override
    public void deleteItem(Long id) {

    }

    @Override
    public void updateItem(RecordItem item) {

    }

    @Override
    public RecordItem queryItem(Long id) {
        return null;
    }

    @Override
    public List<RecordItem> queryAllItems() {
        return null;
    }
}
