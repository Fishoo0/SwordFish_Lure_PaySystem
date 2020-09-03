package com.swordfish.db.room;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.swordfish.base.BaseApplication;
import java.util.List;

public class PaySystemRepository {

    private PaySystemDao mPaySystemDao;

    public static PaySystemRepository INSTANCE = new PaySystemRepository(BaseApplication.INSTANCE);

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public PaySystemRepository(Application application) {
        PaySystemRoomDataBase db = PaySystemRoomDataBase.getDatabase(application);
        mPaySystemDao = db.dao();
    }

    public void addUser(User user) {
        PaySystemRoomDataBase.databaseWriteExecutor.execute(() -> {
            mPaySystemDao.insertUser(user);
        });
    }

    public void updateUser(User user) {
        PaySystemRoomDataBase.databaseWriteExecutor.execute(() -> {
            mPaySystemDao.updateUser(user);
        });
    }

    public LiveData<List<User>> getAllUsers() {
        return mPaySystemDao.getAllUsers();
    }

    MutableLiveData<List<User>> mSearchUsersLiveData = new MutableLiveData<>();

    public LiveData<List<User>> searchUsers(int telephone) {
        PaySystemRoomDataBase.databaseWriteExecutor.execute(() -> {
           List<User> users =  mPaySystemDao.searchUsers(telephone);
           mHandler.post(() -> {
                mSearchUsersLiveData.setValue(users);
           });
        });
       return mSearchUsersLiveData;
    }

    public void addLog(LogItem log) {
        PaySystemRoomDataBase.databaseWriteExecutor.execute(() -> {
            mPaySystemDao.insertUser(log.user);
            mPaySystemDao.insertLog(log.log);
        });
    }

    public void updateLog(LogItem log) {
        PaySystemRoomDataBase.databaseWriteExecutor.execute(() -> {
            mPaySystemDao.updateLog(log.log);
        });
    }

    public LiveData<List<LogItem>> getAllLogs() {
        return mPaySystemDao.getAllLogs();
    }

    public LiveData<List<LogItem>> getTodayLogs(int status) {
        final long currentTime = System.currentTimeMillis();
        final long oneDay = 24 * 60 * 60 * 1000;
        final long startOfToday = ((currentTime / oneDay))  * oneDay;
        final long endOfToday = startOfToday + oneDay;
        return mPaySystemDao.searchLogs(startOfToday,endOfToday,status);
    }

}
