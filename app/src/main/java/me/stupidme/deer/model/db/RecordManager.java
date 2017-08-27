package me.stupidme.deer.model.db;

import java.util.List;

import me.stupidme.deer.model.RecordItem;

/**
 * Created by allen on 17-8-27.
 */

public interface RecordManager {

    boolean insertRecord(RecordItem item);

    boolean deleteRecord(long id);

    boolean deleteRecord(RecordItem item);

    RecordItem queryRecord(long id);

    List<RecordItem> queryRecords();

    boolean updateRecord(RecordItem item);
}
