package com.swordfish.db.room;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "log_table")
public class Log implements Serializable {

    public static final int STATUS_FISHING = 1;
    public static final int STATUS_TIMEOUT = 2;
    public static final int STATUS_FINISHED = 3;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int logId;

    public int paidAmount;
    public int paidType;

    public int fishBackMoney;
    public String fishBackMsg;

    public long startTime;
    public long endTime;

    public String leftMsg;

    public int status = STATUS_FISHING;

    public int rentCardNumber;
    public long userId;

}
