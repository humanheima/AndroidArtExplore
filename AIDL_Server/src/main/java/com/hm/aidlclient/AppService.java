package com.hm.aidlclient;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AppService extends Service {

    private String data = "默认值";
    private boolean running = true;

    public AppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IAppServiceRemoteBinder.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void setData(String data) throws RemoteException {
                AppService.this.data = data;
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("AppService", "onCreate");
        new Thread() {
            @Override
            public void run() {
                while (running) {
                    try {
                        Thread.sleep(1000);
                        Log.e("AppService", "data=" + data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
        Log.e("AppService", "onDestroy");
    }
}
