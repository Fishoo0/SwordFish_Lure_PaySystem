package com.swordfish.db.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "premium_table")
public class Premium {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId;

    public String createTime;

    public int amount;


}
