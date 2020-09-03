package com.swordfish.paysystem.utils;

import android.text.TextUtils;
import android.widget.EditText;

public class Utils {
    public static long parseLong(EditText editText) {
        String value = editText.getText().toString();
        if (!TextUtils.isEmpty(value) && TextUtils.isDigitsOnly(value)) {
            return Long.parseLong(value);
        }
        return 0;
    }

    public static int parseInt(EditText editText) {
        String value = editText.getText().toString();
        if (!TextUtils.isEmpty(value) && TextUtils.isDigitsOnly(value)) {
            return Integer.parseInt(value);
        }
        return 0;
    }
}
