package com.hm.chapter_two;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    private String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        UserManager.userId = 2;
        Log.e(tag, "UserManager.userId=" + UserManager.userId );
    }

    @OnClick(R.id.button)
    public void onClick() {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }
}
