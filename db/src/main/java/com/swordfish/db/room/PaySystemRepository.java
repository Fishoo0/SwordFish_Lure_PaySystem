package com.swordfish.db.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PaySystemRepository implements IPaySystemRepository {

    private PaySystemDao mPaySystemDao;


    public PaySystemRepository(Application application) {
        PaySystemRoomDataBase db = PaySystemRoomDataBase.getDatabase(application);
        mPaySystemDao = db.dao();
    }


    @Override
    public void addUser(User user) {
        PaySystemRoomDataBase.databaseWriteExecutor.execute(() -> {
            mPaySystemDao.insertUser(user);
        });
    }

    @Override
    public LiveData<User> getUser(int telephone) {
        return null;
    }

    @Override
    public LiveData<List<User>> getUsers() {
        return null;
    }

    @Override
    public void addPremium(User user, Premium premium) {

    }

    @Override
    public LiveData<User> getPremium(int telephone) {
        return null;
    }

    @Override
    public LiveData<List<User>> getPremiums() {
        return null;
    }

    @Override
    public void addLog(LogItem log) {

    }

    @Override
    public LiveData<List<LogItem>> getAllLogs() {
        return null;
    }

    @Override
    public LiveData<List<LogItem>> getLogs(int telephone) {
        return null;
    }

    @Override
    public LiveData<List<LogItem>> getTodayLogs() {
        return null;
    }
}
