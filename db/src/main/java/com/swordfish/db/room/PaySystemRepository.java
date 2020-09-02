package com.swordfish.db.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.swordfish.base.BaseApplication;

import java.util.List;

public class PaySystemRepository {

    private PaySystemDao mPaySystemDao;


    public static PaySystemRepository INSTANCE = new PaySystemRepository(BaseApplication.INSTANCE);;


    public PaySystemRepository(Application application) {
        PaySystemRoomDataBase db = PaySystemRoomDataBase.getDatabase(application);
        mPaySystemDao = db.dao();
    }

    public void addUser(User user) {
        PaySystemRoomDataBase.databaseWriteExecutor.execute(() -> {
            mPaySystemDao.insertUser(user);
        });
    }

    public LiveData<List<User>> getAllUsers() {
        return mPaySystemDao.getAllUsers();
    }

    public LiveData<List<User>> searchUsers(int telephone) {
        return mPaySystemDao.searchUsers(telephone);
    }


    public void addLog(LogItem log) {
        mPaySystemDao.insertLog(log);
    }

    public LiveData<List<LogItem>> getAllLogs() {
        return mPaySystemDao.getAllLogs();
    }

    public LiveData<List<LogItem>> searchLogs(int telephone) {

        return null;
    }

    public LiveData<List<LogItem>> getTodayLogs() {
        return null;
    }


    public void addPremium(Premium premium) {
        mPaySystemDao.insertPremium(premium);
    }

    public LiveData<List<Premium>> searchPremium(int telephone) {
        return null;
    }

    public LiveData<List<Premium>> getAllPremiums() {
        return mPaySystemDao.getAllPremiums();
    }


}
