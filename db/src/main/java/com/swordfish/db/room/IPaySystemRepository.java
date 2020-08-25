package com.swordfish.db.room;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IPaySystemRepository {

    void addUser(User user);

    LiveData<User> getUser(int telephone);

    LiveData<List<User>> getUsers();


    void addPremium(User user, Premium premium);

    LiveData<User> getPremium(int telephone);

    LiveData<List<User>> getPremiums();


    void addLog(ConsumeLog log);

    LiveData<List<ConsumeLog>> getAllLogs();

    LiveData<List<ConsumeLog>> getLogs(int telephone);

    LiveData<List<ConsumeLog>> getTodayLogs();


}
