package com.swordfish.paysystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.swordfish.paysystem.view.AddLogActivity;
import com.swordfish.paysystem.view.FinishedFragment;
import com.swordfish.paysystem.view.FishingFragment;
import com.swordfish.paysystem.view.TimeOutFragment;
import com.swordfish.paysystem.view.LogListFragment;

import java.util.HashMap;
import java.util.Map;


public class WorkingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.working_act);

        ViewPager viewPager = findViewById(R.id.view_pager);

        TabLayout tab = findViewById(R.id.viw_pager_tablayout);
        tab.setupWithViewPager(viewPager, true);

        Map<Integer, LogListFragment> map = new HashMap();
        map.put(0, new FishingFragment(0));
        map.put(1, new TimeOutFragment(1));
        map.put(2, new FinishedFragment(2));

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
                return ((LogListFragment) map.get(position)).getTitle();
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_customer:
                startActivity(new Intent(this, AddLogActivity.class));
                break;
        }
    }
}
