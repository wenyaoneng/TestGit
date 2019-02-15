package aidl.vincent.com.aidltest;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by Vincent.Wen on 2017/10/20.
 */

public class MyService extends Service {
    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }
    private  IBinder binder = new IMyFoo.Stub(){
        @Override
        public byte[] foo(byte[] bs) throws RemoteException {
            boolean b = Looper.getMainLooper().getThread() == Thread.currentThread();//这里给了一个是否为UI线程调用的判断
            byte[] newData = Arrays.copyOf(bs, bs.length + 1);
            newData[newData.length - 1] = (byte)(b ? 1 : 0);
            return newData;
        }

        @Override
        public String getThreadInfo() throws RemoteException {
            Looper looper =  Looper.getMainLooper();

            Log.i("AIDLTEST", "mainthread:"+(Looper.getMainLooper().getThread()==Thread.currentThread()));
            return "isUI:"+(Looper.getMainLooper().getThread()==Thread.currentThread())+":"+getPackageName();
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("AIDLTEST", "Service.onCreate:"+(Looper.getMainLooper().getThread()==Thread.currentThread()));
    }
}
