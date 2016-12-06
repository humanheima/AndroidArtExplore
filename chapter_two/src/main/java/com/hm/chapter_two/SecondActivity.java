package com.hm.chapter_two;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.button2)
    Button button2;
    private String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        Log.e(tag, "UserManager.userId=" + UserManager.userId);
    }

    @OnClick(R.id.button2)
    public void onClick() {
        startActivity(new Intent(this, ThirdActivity.class));
    }
}
