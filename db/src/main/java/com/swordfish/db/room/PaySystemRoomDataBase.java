package com.swordfish.db.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Log.class}, version = 1, exportSchema = false)
public abstract class PaySystemRoomDataBase extends RoomDatabase {

    public abstract PaySystemDao dao();

    private static volatile PaySystemRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PaySystemRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PaySystemRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PaySystemRoomDataBase.class,"pay_system_database").build();
                }
            }
        }
        return INSTANCE;
    }
}

