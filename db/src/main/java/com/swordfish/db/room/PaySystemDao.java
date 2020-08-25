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
    LiveData<List<User>> getUsers();


}
