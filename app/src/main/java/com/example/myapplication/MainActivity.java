package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Handler h;
    private TextView tv;
    private Button btn, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        // Handler позволяет отправлять сообщения в другие потоки с задержкой или без
        // Looper запускает цикл обработки сообщений, связанный с потоком - повторитель
        // getMainlooper() - цикл в main потоке
        h = new Handler(Looper.getMainLooper()) {
            @Override
            // обработать сообщение и отобразить его в UI потоке
            public void handleMessage(@NonNull Message msg) {
                //  tv.setText("Сделано операций: "+msg.what);
            }
        };
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 10; i++) {
                            doSlow();
                            tv.setText("Сделано операций: "+i);
                            //h.sendEmptyMessage(i);
                            Log.d("RRRRR","Сделано операций: " + i);
                        }
                    }
                });
                thread.start();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("RRRRR","TEST!!!");
            }
        });
    }

    public void doSlow() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}