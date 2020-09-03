package com.swordfish.paysystem;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.swordfish.base.BaseApplication;
import com.swordfish.base.PermissionUtils;

public class SplashActivity extends AppCompatActivity {

    static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        getSupportActionBar().hide();

        ((BaseApplication) getApplication()).getGlobalUIHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkCommonPermissions();
            }
        }, 2000);

    }


    @Override
    protected void onResume() {
        super.onResume();



    }

    private void startApp() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
    }


    private void exitApp(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        builder.setMessage(message);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                SplashActivity.this.finish();
            }
        });
        builder.setPositiveButton("退出程序", null);
        builder.create().show();
    }

    private void checkCommonPermissions() {
        Log.v(TAG, "checkCommonPermissions");

        if (PermissionUtils.askForPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET)) {
            Log.v(TAG, " passed");
            startApp();
        } else {
            Log.e(TAG, " wait for user's permission ...");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean result = PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        if (result) {
            Log.v(TAG, " onRequestPermissionsResult passed");
            checkCommonPermissions();
        } else {
            Log.v(TAG, " onRequestPermissionsResult denied");
            exitApp("您已强制取消权限，请到设置里面设置好权限再启动APP");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}
