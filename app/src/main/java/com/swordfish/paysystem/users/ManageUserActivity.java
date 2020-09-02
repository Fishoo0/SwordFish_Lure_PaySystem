package com.swordfish.paysystem.users;


import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.swordfish.db.room.User;
import com.swordfish.paysystem.R;

import java.util.List;

/**
 * This is activity is designed for adding/searching/premium and etc ...
 */
public class ManageUserActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mTelEdit;
    EditText mNameEdit;
    EditText mPayEdit;

    ListPopupWindow mSearchPopWindow;

    DataBaseViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_user_act);

        mTelEdit = findViewById(R.id.telephone_edittext);
        mNameEdit = findViewById(R.id.name_edittext);
        mPayEdit = findViewById(R.id.amout_edittext);

        mViewModel = DataBaseViewModel.INSTANCE;


        ListPopupWindow window = new ListPopupWindow(this);
        PopWindowAdapter adapter = new PopWindowAdapter(mViewModel.mUsersSearchedByPhone);
        window.setAdapter(adapter);
        window.setDropDownGravity(Gravity.BOTTOM);
        window.setAnchorView(findViewById(R.id.telephone_edittext));

        mSearchPopWindow = window;

        mTelEdit.addTextChangedListener(new TextWatcher() {

            Handler handler = new Handler();

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewModel.searchUsers(Integer.parseInt(charSequence.toString()));
                    }
                }, 400);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:

                mViewModel.mRepository.addUser(null);

                break;
        }
    }


    class PopWindowAdapter extends BaseAdapter {


        private List<User> mList;

        public PopWindowAdapter(LiveData<List<User>> data) {
            data.observe(ManageUserActivity.this, new Observer<List<User>>() {
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
            if (view.getTag() == null) {
                ViewHolder holder = new ViewHolder(LayoutInflater.from(viewGroup.getContext()));
                view.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.updateView((User) getItem(i));
            return holder.view;
        }


        class ViewHolder {
            View view;

            TextView id;
            TextView telephone;
            TextView name;

            public ViewHolder(LayoutInflater layoutInflater) {
                view = layoutInflater.inflate(R.layout.user_list_item, null);
                telephone = view.findViewById(R.id.telephone);
                name = view.findViewById(R.id.name);
            }

            void updateView(User user) {
                id.setText(user.id);
                telephone.setText(user.telephone);
                name.setText(user.name);
            }
        }
    }

}
