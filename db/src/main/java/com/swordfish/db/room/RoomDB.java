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
    public void addLog(ConsumeLog log) {

    }

    @Override
    public List<ConsumeLog> getAllLogs() {
        return null;
    }

    @Override
    public List<ConsumeLog> getLogs(int telephone) {
        return null;
    }

    @Override
    public List<ConsumeLog> getTodayLogs() {
        return null;
    }
}
