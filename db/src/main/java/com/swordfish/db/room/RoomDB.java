package com.swordfish.db.room;

import java.util.List;

public class RoomDB implements IPaySystemRepository {

    @Override
    public void addUser(User user) {

    }

    @Override
    public User getUser(int telephone) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void addPremium(User user, Premium premium) {

    }

    @Override
    public Premium getPremium(int telephone) {
        return null;
    }

    @Override
    public List<User> getPremiums() {
        return null;
    }

    @Override
    public void addLog(LogItem log) {

    }

    @Override
    public List<LogItem> getAllLogs() {
        return null;
    }

    @Override
    public List<LogItem> getLogs(int telephone) {
        return null;
    }

    @Override
    public List<LogItem> getTodayLogs() {
        return null;
    }
}
