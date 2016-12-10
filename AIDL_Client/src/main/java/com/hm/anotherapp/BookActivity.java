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
import android.widget.Button;

import com.hm.aidlclient.BookManager;
import com.hm.core.model.Book;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookActivity extends AppCompatActivity {

    @BindView(R.id.btn_book)
    Button btnBook;
    private static String tag = "BookActivity";
    private BookManager bookManager;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            bookManager = BookManager.Stub.asInterface(iBinder);

            try {
                List<Book> books = bookManager.getBookList();
                Log.e(tag, "query book list ,list type:" + books.getClass().getCanonicalName());
                Log.e(tag, "query book list ,list type:" + books.toString());
                Book book = new Book(3, "android开发艺术探索");
                bookManager.addBook(book);
                Log.e(tag, "add book:" + book);
                List<Book> bookList = bookManager.getBookList();
                Log.e(tag, "query book list ,list type:" + bookList.getClass().getCanonicalName());
                Log.e(tag, "query book list ,list type:" + bookList.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_book)
    public void onClick() {
        Intent serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.hm.aidlclient", "com.hm.aidlclient.BookManagerService"));
        bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
