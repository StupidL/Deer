package me.stupidme.deer.model;

import java.util.List;

/**
 * Created by allen on 17-8-6.
 */

public interface RecordItemModel {

    void insertItem(RecordItem item);

    void deleteItem(RecordItem item);

    void deleteItem(Long id);

    void updateItem(RecordItem item);

    RecordItem queryItem(Long id);

    List<RecordItem> queryAllItems();
}
