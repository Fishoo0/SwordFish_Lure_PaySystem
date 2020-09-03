package com.swordfish.db.room;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "log_table")
public class Log {

    public static final int STATUS_FISHING = 1;
    public static final int STATUS_TIMEOUT = 2;
    public static final int STATUS_FINISHED = 3;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int logId;

    public int paidAmount;
    public int paidType;

    public int backPaidAmount;
    public int backPaidType;

    public int createTime;
    public int endTime;

    public int status = STATUS_FISHING;

    public int rentCardNumber;
    public int userId;

}
