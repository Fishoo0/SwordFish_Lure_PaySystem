package com.swordfish.paysystem.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.swordfish.db.room.User;
import com.swordfish.paysystem.R;

public class UserListItemView extends FrameLayout {

    public UserListItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    TextView telephone;
    TextView name;

    void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.user_list_item, this);
        telephone = findViewById(R.id.telephone);
        name = findViewById(R.id.name);
    }

    public void updateView(User data) {
        telephone.setText(data.telephone + "");
        name.setText(data.name);
    }

}
