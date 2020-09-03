package com.swordfish.db.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PaySystemDao {

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM user_table ORDER BY userId ASC")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM user_table WHERE userId LIKE :telephone")
    List<User> searchUsers(int telephone);


    @Insert
    void insertLog(Log log);

    @Update
    void updateLog(Log log);


    @Transaction
    @Query("SELECT * FROM log_table ORDER BY logId ASC")
    LiveData<List<LogItem>> getAllLogs();

    @Transaction
    @Query("SELECT * FROM log_table WHERE createTime > :timeStart AND createTime < :timeEnd AND status = :status")
    LiveData<List<LogItem>> searchLogs(long timeStart, long timeEnd, int status);

}
