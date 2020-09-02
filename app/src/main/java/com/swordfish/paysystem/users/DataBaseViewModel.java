package com.swordfish.paysystem.users;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.swordfish.db.room.LogItem;
import com.swordfish.db.room.PaySystemRepository;
import com.swordfish.db.room.User;

import java.util.ArrayList;
import java.util.List;

public class DataBaseViewModel extends ViewModel implements LifecycleOwner {

    public static DataBaseViewModel INSTANCE = new DataBaseViewModel();

    public MutableLiveData<List<LogItem>> mFishingLogs = new MutableLiveData<>();

    public MutableLiveData<List<LogItem>> mTimeOutLogs = new MutableLiveData<>();

    public MutableLiveData<List<LogItem>> mFinishedLogs = new MutableLiveData<>();


    public MutableLiveData<List<User>> mUsersSearchedByPhone = new MutableLiveData<>();


    public final PaySystemRepository mRepository = PaySystemRepository.INSTANCE;

    public DataBaseViewModel() {

        List<LogItem> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LogItem item = new LogItem();
            item.id = i;
            item.user = new User();
            item.user.name = "Name " + i;
            item.user.telephone = i * 1000;
        }
        mFishingLogs.setValue(items);


        List<LogItem> items1 = new ArrayList<>();
        for (int i = 10; i < 20; i++) {
            LogItem item1 = new LogItem();
            item1.id = i;
            item1.user = new User();
            item1.user.name = "Name " + i;
            item1.user.telephone = i * 1000;
        }
        mTimeOutLogs.setValue(items1);


        List<LogItem> items2 = new ArrayList<>();
        for (int i = 20; i < 30; i++) {
            LogItem item2 = new LogItem();
            item2.id = i;
            item2.user = new User();
            item2.user.name = "Name " + i;
            item2.user.telephone = i * 1000;
        }
        mFinishedLogs.setValue(items2);
    }

    public LiveData<List<LogItem>> getLogs(int index) {

        if (index == 0) {
            return mFishingLogs;
        } else if (index == 1) {
            return mTimeOutLogs;
        } else {
            return mFinishedLogs;
        }
    }




    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return new Lifecycle() {
            @Override
            public void addObserver(@NonNull LifecycleObserver observer) {

            }

            @Override
            public void removeObserver(@NonNull LifecycleObserver observer) {

            }

            @NonNull
            @Override
            public State getCurrentState() {
                return State.STARTED;
            }
        };

    }



    public void addCustomer(int telephone, int name, int amount, int cardNumber) {

    }


    public void searchUsers(int phone) {
        mRepository.searchUsers(phone).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                mUsersSearchedByPhone.setValue(users);
            }
        });
    }


    void  timeOutCustomer(LogItem log) {

    }


    void  finishCustomer(LogItem log) {

    }
}
