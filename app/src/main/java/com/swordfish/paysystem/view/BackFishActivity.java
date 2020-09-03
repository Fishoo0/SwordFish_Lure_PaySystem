package com.swordfish.paysystem.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.swordfish.db.room.LogItem;
import com.swordfish.paysystem.R;

import static com.swordfish.paysystem.utils.Utils.parseInt;

public class BackFishActivity extends AppCompatActivity {

    private final DataBaseViewModel mViewModel = DataBaseViewModel.INSTANCE;

    EditText mBackMoney;
    EditText mBackMsg;

    LogItem mLogItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_fish_act);

        mLogItem = (LogItem) getIntent().getSerializableExtra("logItem");

        UserListItemView userView = findViewById(R.id.user_list_itemview);
        userView.updateView(mLogItem.user);

        mBackMoney = findViewById(R.id.fish_back_edittext);
        mBackMsg = findViewById(R.id.fish_back_msg_edittext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_confirm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_confirm:
                mLogItem.log.fishBackMoney = parseInt(mBackMoney);
                mLogItem.log.leftMsg = mBackMsg.getText().toString();
                mViewModel.finishLogItem(mLogItem);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static final void jumpToThis(Context context, LogItem logItem) {
        Intent i = new Intent(context, BackFishActivity.class);
        i.putExtra("logItem", logItem);
        context.startActivity(i);
    }

}
