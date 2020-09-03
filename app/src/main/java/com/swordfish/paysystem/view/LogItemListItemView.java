package com.swordfish.paysystem.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.swordfish.db.room.LogItem;
import com.swordfish.paysystem.R;

public class LogItemListItemView extends FrameLayout {

    public LogItemListItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    UserListItemView userView;

    TextView money;
    TextView time;
    TextView cardNo;

    TextView fishBack;
    TextView fishBackMsg;
    TextView earn;

    TextView extra;

    TextView timeLeft;


    void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.log_list_item_layout, this);

        userView = findViewById(R.id.user_list_itemview);

        money = findViewById(R.id.money);
        time = findViewById(R.id.time);
        timeLeft = findViewById(R.id.time_left);
        cardNo = findViewById(R.id.card_no);
        fishBack = findViewById(R.id.fish_back);
        fishBackMsg = findViewById(R.id.fish_back_msg);
        earn = findViewById(R.id.earn);
        extra = findViewById(R.id.extra_msg);
    }


    public void updateView(LogItem data) {
        userView.updateView(data.user);

        money.setText(data.log.paidAmount + "");
        time.setText(data.log.startTime + " ~ " + data.log.endTime);
        timeLeft.setText((System.currentTimeMillis() - data.log.endTime)/(1000 * 60) + "分钟");
        cardNo.setText(data.log.rentCardNumber + "");
        fishBack.setText(data.log.fishBackMoney + "");
        fishBackMsg.setText(data.log.fishBackMsg + "");
        earn.setText((data.log.paidAmount - data.log.fishBackMoney) + "");
        extra.setText(data.log.leftMsg);
    }

}
