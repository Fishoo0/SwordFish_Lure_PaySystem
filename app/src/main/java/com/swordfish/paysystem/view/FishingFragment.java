package com.swordfish.paysystem.view;

import android.view.View;
import android.widget.AdapterView;

import com.swordfish.db.room.LogItem;


public class FishingFragment extends LogListFragment {

    public FishingFragment(int index) {
        super(index, "Fishing");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        LogItem data = (LogItem) adapterView.getAdapter().getItem(i);
        mViewModel.timeOutLogItem(data);
    }
}
