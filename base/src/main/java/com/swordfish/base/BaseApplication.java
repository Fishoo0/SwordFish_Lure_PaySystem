package  com.swordfish.base;


import android.app.Application;
import android.os.Handler;

public class BaseApplication extends Application {

    public static BaseApplication INSTANCE = null;

    private Handler mGlobalUiHandler = new Handler();


    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        INSTANCE = null;
    }


    public Handler getGlobalUIHandler() {
        return mGlobalUiHandler;
    }

}