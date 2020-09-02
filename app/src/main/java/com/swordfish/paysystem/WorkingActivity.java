package com.swordfish.paysystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.swordfish.paysystem.users.FinishedUsersFragment;
import com.swordfish.paysystem.users.FishingUsersFragment;
import com.swordfish.paysystem.users.TimeOutUsersFragment;
import com.swordfish.paysystem.users.UserListFragment;

import java.util.HashMap;
import java.util.Map;


public class WorkingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.working_act);

        ViewPager viewPager = findViewById(R.id.view_pager);

        TabLayout tab = findViewById(R.id.viw_pager_tablayout);
        tab.setupWithViewPager(viewPager,true);

        Map<Integer, UserListFragment> map = new HashMap();
        map.put(0, new FishingUsersFragment(0));
        map.put(1, new TimeOutUsersFragment(1));
        map.put(2, new FinishedUsersFragment(2));

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return map.get(position);
            }

            @Override
            public int getCount() {
                return map.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return ((UserListFragment)map.get(position)).getTitle();
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);
    }
}
