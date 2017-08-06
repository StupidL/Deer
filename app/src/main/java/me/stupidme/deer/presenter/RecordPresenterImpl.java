package me.stupidme.deer.presenter;

import java.util.List;

import me.stupidme.deer.RecordItemView;
import me.stupidme.deer.model.RecordItem;
import me.stupidme.deer.model.RecordItemModel;
import me.stupidme.deer.model.RecordItemModelImpl;

/**
 * Created by allen on 17-8-6.
 */

public class RecordPresenterImpl implements RecordPresenter {

    private RecordItemView mView;

    private RecordItemModel mModel;

    public RecordPresenterImpl(RecordItemView view) {
        mView = view;
        mModel = new RecordItemModelImpl();
    }

    @Override
    public void loadRecords() {
        List<RecordItem> items = mModel.queryAllItems();
        for (RecordItem item : items)
            mView.addItem(item);
    }
}
