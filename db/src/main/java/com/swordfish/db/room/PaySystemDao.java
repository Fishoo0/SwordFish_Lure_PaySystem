package com.swordfish.db.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PaySystemDao {

    @Insert
    void insertUser(User user);


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    LiveData<List<User>> getAllUsers();


    @Query("SELECT * FROM user_table WHERE telephone LIKE :telephone")
    LiveData<List<User>> searchUsers(int telephone);


    @Insert
    void insertLog(LogItem log);


    @Query("SELECT * FROM log_table ORDER BY id ASC")
    LiveData<List<LogItem>> getAllLogs();


    @Query("SELECT * FROM log_table WHERE id LIKE :id")
    LiveData<List<LogItem>> searchLogs(int id);


    @Insert
    void insertPremium(Premium premium);


    @Query("SELECT * FROM premium_table ORDER BY id ASC")
    LiveData<List<Premium>> getAllPremiums();


    @Query("SELECT * FROM premium_table WHERE id LIKE :id")
    LiveData<List<LogItem>> searchPremiums(int id);

}
