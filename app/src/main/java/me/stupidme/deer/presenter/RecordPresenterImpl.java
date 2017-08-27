package me.stupidme.deer.presenter;

import java.util.List;

import me.stupidme.deer.RecordItemView;
import me.stupidme.deer.model.RecordItem;
import me.stupidme.deer.model.db.RecordManager;
import me.stupidme.deer.model.db.RecordManagerImpl;

/**
 * Created by allen on 17-8-6.
 */

public class RecordPresenterImpl implements RecordPresenter {

    private RecordItemView mView;

    private RecordManager mRecordManager;

    public RecordPresenterImpl(RecordItemView view) {
        mView = view;
        mRecordManager = RecordManagerImpl.getInstance();
    }

    @Override
    public void loadRecords() {
        List<RecordItem> items = mRecordManager.queryRecords();
        for (RecordItem item : items)
            mView.addItem(item);
    }
}
