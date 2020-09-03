package com.swordfish.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fishyu on 2018/10/30.
 */

public class PermissionUtils {


    public static final String TAG = PermissionUtils.class.getSimpleName();


    public static final int PERMISSION_REQUEST_CODE = 332;


    /**
     * Ask for permissions.
     *
     * @param activity
     * @param permissions
     */
    public static boolean askForPermission(Activity activity, String... permissions) {
        List<String> list = null;
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                if (list == null) {
                    list = new ArrayList<String>();
                }
                list.add(permission);
            }
        }
        if (list == null) {
            return true;
        } else {
            ActivityCompat.requestPermissions(activity, list.toArray(new String[]{}), PERMISSION_REQUEST_CODE);
            return false;
        }
    }


    public static boolean onRequestPermissionsResult(Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int i = 0; i < grantResults.length; i++) {
            int result = grantResults[i];
            if (result != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "You have canceled permission -> " + permissions[i], Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }


    @Deprecated
    public static boolean hasPermission(Activity activity, String permission) {
        return ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

}


