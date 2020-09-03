package com.swordfish.paysystem.view;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.swordfish.db.room.Log;
import com.swordfish.db.room.LogItem;
import com.swordfish.db.room.PaySystemRepository;
import com.swordfish.db.room.User;

import java.util.List;

public class DataBaseViewModel extends ViewModel implements LifecycleOwner {

    public static final DataBaseViewModel INSTANCE = new DataBaseViewModel();

    public final PaySystemRepository mRepository = PaySystemRepository.INSTANCE;

    public final LiveData<List<User>> mAllUsers = mRepository.getAllUsers();

    public final LiveData<List<LogItem>> mAllLogs = mRepository.getAllLogs();

    public final LiveData<List<LogItem>> mFishingLogs = mRepository.getTodayLogs(Log.STATUS_FISHING);

    public final LiveData<List<LogItem>> mTimeOutLogs = mRepository.getTodayLogs(Log.STATUS_TIMEOUT);

    public final LiveData<List<LogItem>> mFinishedLogs = mRepository.getTodayLogs(Log.STATUS_FINISHED);

    public final LiveData<List<User>> mUsersSearchedByPhone = mRepository.searchUsers(0);

    private final TimeOutRefresher mRefresher = new TimeOutRefresher();

    private final InternalLifeCycle mLifeCycle = new InternalLifeCycle();

    public DataBaseViewModel() {
        mRefresher.start();
        mLifeCycle.setState(Lifecycle.State.CREATED);
    }

    class InternalLifeCycle extends Lifecycle {

        State state = State.CREATED;

        void setState(State state) {
            this.state = state;
        }

        @Override
        public void addObserver(@NonNull LifecycleObserver observer) {
        }

        @Override
        public void removeObserver(@NonNull LifecycleObserver observer) {
        }

        @NonNull
        @Override
        public State getCurrentState() {
            return null;
        }
    }

    ;

    @Override
    protected void onCleared() {
        super.onCleared();
        mRefresher.removeCallbacksAndMessages(null);
        mLifeCycle.setState(Lifecycle.State.DESTROYED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifeCycle;
    }

    public LiveData<List<LogItem>> getLogs(int index) {
        if (index == 0) {
            return mFishingLogs;
        } else if (index == 1) {
            return mTimeOutLogs;
        } else if (index == 2) {
            return mFinishedLogs;
        } else {
            throw new RuntimeException("Can not get logs, invalid params of index -> " + index);
        }
    }


    public void addLogItem(long telephone, String name, int amount, int cardNumber) {
        User user = null;

        if (mAllUsers.getValue() != null) {
            for (User u : mAllUsers.getValue()) {
                if (user.telephone == telephone) {
                    user = u;
                    break;
                }
            }
        }

        if (user == null) {
            user = new User();
            user.telephone = telephone;
            user.name = name;
        }

        LogItem logItem = new LogItem();
        logItem.user = user;
        logItem.log = new Log();
        logItem.log.userId = user.telephone;
        logItem.log.paidAmount = amount;
        logItem.log.startTime = System.currentTimeMillis();
        logItem.log.rentCardNumber = cardNumber;

        logItem.log.status = Log.STATUS_FISHING;

        mRepository.addLog(logItem);
    }

    public void searchUsers(long phone) {
        mRepository.searchUsers(phone);
    }

    final class TimeOutRefresher extends Handler {

        void start() {
            sendEmptyMessage(0);
        }

        void refreshTimeOut() {
            if (mFishingLogs.getValue() != null) {
                for (LogItem item : mFishingLogs.getValue()) {
                    if (System.currentTimeMillis() > item.log.endTime) {
                        timeOutLogItem(item);
                    }
                }
            }
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            refreshTimeOut();
            sendEmptyMessageDelayed(0, 10 * 1000);
        }
    }

    void timeOutLogItem(LogItem log) {
        log.log.status = Log.STATUS_TIMEOUT;
        mRepository.updateLog(log);
    }

    void finishLogItem(LogItem log) {
        log.log.status = Log.STATUS_FINISHED;
        mRepository.updateLog(log);
    }

}
