package com.swordfish.db.room;


import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;

public class LogItem implements Serializable {

    @Embedded
    public Log log;

    @Relation(
            parentColumn = "userId",
            entityColumn = "userId"
    )
    public User user;

}
