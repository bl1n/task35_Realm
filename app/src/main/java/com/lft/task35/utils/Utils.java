package com.lft.task35.utils;

import android.text.TextUtils;
import android.widget.EditText;

public class Utils {

    public static boolean isCheckedFields(EditText... editTexts) {
        for (EditText e : editTexts) {
            if (TextUtils.isEmpty(e.getText()))
                return false;
        }
    return true;
    }
}
