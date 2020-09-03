package com.swordfish.db.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "userId")
    public int telephone;

    public String name;

    public String wechat;
    public String location;

    public String inviter;

    @Embedded
    public Premium premium;

    public static class Premium {
        public int premiumId;
        public String createTime;
        public int amount;
    }


}
