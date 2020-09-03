package com.swordfish.paysystem.view;

import android.view.View;
import android.widget.AdapterView;

import com.swordfish.db.room.LogItem;

public class TimeOutFragment extends LogListFragment {

    public TimeOutFragment(int index) {
        super(index, "TimeOut");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        LogItem data = (LogItem) adapterView.getAdapter().getItem(i);
        BackFishActivity.jumpToThis(this.getActivity(), data);
    }
}
