package aidl.vincent.com.aidltest;

import android.app.Application;
import android.util.Log;

/**
 * Created by Vincent.Wen on 2017/10/20.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("AIDLTEST", "onCreate: "+hashCode());
    }
}
