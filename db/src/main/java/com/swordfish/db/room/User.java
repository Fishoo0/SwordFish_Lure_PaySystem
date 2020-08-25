package com.swordfish.db.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;


    public int telephone;

    public String name;
    public String wechat;
    public String carNumber;
    public String location;
    public String inviter;

    public Premium premium;

}
