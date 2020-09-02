package com.swordfish.db.room;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IPaySystemRepository {

    void addUser(User user);

    LiveData<User> getUser(int telephone);

    LiveData<List<User>> getUsers();

    LiveData<List<User>> searchUsers(int telephone);



    void addPremium(User user, Premium premium);

    LiveData<User> getPremium(int telephone);

    LiveData<List<User>> getPremiums();



    void addLog(LogItem log);

    LiveData<List<LogItem>> getAllLogs();

    LiveData<List<LogItem>> getLogs(int telephone);

    LiveData<List<LogItem>> getTodayLogs();


}
