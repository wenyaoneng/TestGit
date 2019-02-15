package aidl.vincent.com.aidltest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private String TAG="AIDLTEST";
    private IMyFoo aidl ;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidl = IMyFoo.Stub.asInterface(service);
            try {
                Log.i(TAG, "onServiceConnected: ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: ");
            aidl=null;
        }
    };

    private void bindService() {

        Intent intent = new Intent();
        //绑定服务端的service
        intent.setAction("com.mecury.aidltest.IRomoteService");
        //新版本（5.0后）必须显式intent启动 绑定服务
//        intent.setClass(this, MyService.class);
        intent.setComponent(new ComponentName("aidl.vincent.com.aidltest","aidl.vincent.com.aidltest.MyService"));
        //绑定的时候服务端自动创建
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Test.cal();
                try {
                    byte[] b = new byte[]{3,5,6,7,88,7};
                    Log.i(TAG, "onClick: "+ Arrays.toString(aidl.foo(b))+"  t: "+aidl.getThreadInfo());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        bindService();
        Test.cal();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
