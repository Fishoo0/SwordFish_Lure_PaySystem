package com.swordfish.db.room;


import androidx.room.Embedded;
import androidx.room.Relation;

public class LogItem {

    @Embedded
    public Log log;

    @Relation(
            parentColumn = "logId",
            entityColumn = "userId"
    )
    public User user;

}
