package com.swordfish.paysystem.view;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.swordfish.db.room.User;
import com.swordfish.paysystem.R;

import java.util.List;

import static com.swordfish.paysystem.utils.Utils.parseInt;
import static com.swordfish.paysystem.utils.Utils.parseLong;

/**
 * This is activity is designed for adding/searching/premium and etc ...
 */
public class AddLogActivity extends AppCompatActivity {

    EditText mTelEdit;
    EditText mNameEdit;
    EditText mPaidEdit;
    EditText mRentCardEdit;
    ListPopupWindow mSearchPopWindow;

    DataBaseViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_log_act);

        mTelEdit = findViewById(R.id.telephone_edittext);
        mNameEdit = findViewById(R.id.name_edittext);
        mPaidEdit = findViewById(R.id.paid_edittext);
        mRentCardEdit = findViewById(R.id.rent_card_edittext);

        mViewModel = DataBaseViewModel.INSTANCE;

        ListPopupWindow window = new ListPopupWindow(this);
        PopWindowAdapter adapter = new PopWindowAdapter(mViewModel.mUsersSearchedByPhone);
        window.setAdapter(adapter);
        window.setDropDownGravity(Gravity.BOTTOM);
        window.setAnchorView(findViewById(R.id.telephone_edittext));

        mSearchPopWindow = window;

        mTelEdit.addTextChangedListener(new TextWatcher() {

            Handler handler = new Handler(Looper.getMainLooper());

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewModel.searchUsers(parseLong(mTelEdit));
                    }
                }, 400);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
                mViewModel.addLogItem(parseLong(mTelEdit), mNameEdit.getText().toString(), parseInt(mPaidEdit), parseInt(mRentCardEdit));
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class PopWindowAdapter extends BaseAdapter {

        private List<User> mList;

        public PopWindowAdapter(LiveData<List<User>> data) {
            data.observe(AddLogActivity.this, new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    mList = users;
                    if (getCount() > 0) {
                        mSearchPopWindow.show();
                    } else {
                        mSearchPopWindow.dismiss();
                    }
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public Object getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            UserListItemView itemView;
            if (view == null) {
                itemView = new UserListItemView(viewGroup.getContext(), null);
            } else {
                itemView = (UserListItemView) view;
            }
            itemView.updateView((User) getItem(i));
            return itemView;
        }

    }

}
