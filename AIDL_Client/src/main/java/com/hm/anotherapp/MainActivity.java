package com.hm.anotherapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hm.aidlclient.IAppServiceRemoteBinder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.btn_bind)
    Button btnBind;
    @BindView(R.id.btn_unbind)
    Button btnUnbind;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.btn_edit)
    Button btnEdit;
    private Intent serviceIntent;
    private IAppServiceRemoteBinder binder = null;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("AppService", "onServiceConnected" + iBinder);
            binder = IAppServiceRemoteBinder.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("AppService", "onServiceDisconnected" + componentName);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.hm.aidlclient", "com.hm.aidlclient.AppService"));
    }

    @OnClick({R.id.btn_start, R.id.btn_stop, R.id.btn_bind, R.id.btn_unbind, R.id.btn_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startService(serviceIntent);
                break;
            case R.id.btn_stop:
                stopService(serviceIntent);
                break;
            case R.id.btn_bind:
                bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                unbindService(connection);
                break;
            case R.id.btn_edit:
                if (binder != null) {
                    try {
                        binder.setData(editText.getText().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }


}
