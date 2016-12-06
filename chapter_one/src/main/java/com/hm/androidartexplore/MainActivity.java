package com.hm.androidartexplore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String tag = "MainActivity";
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //在onCreate方法中要判断savedInstanceState是否为空
        if (savedInstanceState != null) {
            String result = savedInstanceState.getString("result", "hahaha");
            Log.e(tag, "onCreate" + result);
        }
        text = (TextView) findViewById(R.id.text_start_main_activity);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.hm.chapter_1.c");
                intent.addCategory("com.hm.category.c");
                intent.setDataAndType(Uri.parse("file://abc"), "text/plain");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "activity,不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(tag, "onNewIntent");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //无需判断savedInstanceState是否为空
        String result = savedInstanceState.getString("result", "hahaha");
        Log.e(tag, "onRestoreInstanceState" + result);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(tag, "onSaveInstanceState");
        outState.putString("result", "hm is a beautiful girl");
    }
}
