package com.swordfish.paysystem.users;

import android.view.View;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;

public class FinishedUsersFragment extends UserListFragment {

    public FinishedUsersFragment(int index) {
        super(index,"Finished");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
