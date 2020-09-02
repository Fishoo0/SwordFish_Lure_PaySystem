package com.swordfish.paysystem.users;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swordfish.db.room.LogItem;

import java.util.List;

public class UserListViewModel extends ViewModel {

    public LiveData<List<LogItem>> mFishingLogs = new MutableLiveData<>();

    public LiveData<List<LogItem>> mTimeOutLogs = new MutableLiveData<>();

    public LiveData<List<LogItem>> mFinishedLogs = new MutableLiveData<>();


    public LiveData<List<LogItem>> getLogs(int index) {

        if(index == 0) {
            return mFishingLogs;
        } else if (index == 1) {
            return mTimeOutLogs;
        } else {
            return mFinishedLogs;
        }
    }

}
