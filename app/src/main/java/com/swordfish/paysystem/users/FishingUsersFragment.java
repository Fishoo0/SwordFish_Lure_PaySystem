package com.swordfish.paysystem.users;

import android.view.View;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;


public class FishingUsersFragment extends UserListFragment {

    public FishingUsersFragment(int index) {
        super(index,"Fishing");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
