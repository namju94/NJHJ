package com.example.namju.njhj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Main3Activity extends AppCompatActivity {

    TextView textView1; // 전자책 제목
    String epubName;
    String encodeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView1 = (TextView) findViewById(R.id.textView1);

        Intent intent = getIntent();
        epubName = intent.getExtras().getString("EPUBNAME");
        textView1.setText(epubName);
/*
        encodeResult = null;
        try {
            encodeResult = URLEncoder.encode(epubName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(Intent.ACTION_VIEW); // Server
        Uri u = Uri.parse("http://computer.kevincrack.com/download.jsp?name=" + encodeResult);
        i.setData(u);
        startActivity(i);
        */
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            Object path = message.obj;                                  // RESULT_OK
            if (message.arg1 == RESULT_OK && path != null) {
                Toast.makeText(getApplicationContext(), "" + path.toString() + "을 다운로드하였음.", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "다운로드 실패", Toast.LENGTH_LONG).show();
            }
        } ;
    };

    public void onClick(View view) {
        encodeResult = null;
        try {
            encodeResult = URLEncoder.encode(epubName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(Intent.ACTION_VIEW); // Server
        Uri u = Uri.parse("http://computer.kevincrack.com/download.jsp?name=" + encodeResult);
        i.setData(u);
        startActivity(i);


        /*
        encodeResult = null;
        try {
            encodeResult = URLEncoder.encode(epubName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String urlResult = "http://computer.kevincrack.com/download.jsp?name=" + encodeResult;

        Intent intent = new Intent(this, MyIntentService.class);
        Messenger messenger = new Messenger(handler);
        intent.putExtra("MESSENGER", messenger);
        //intent.setData(Uri.parse("http://computer.kevincrack.com/download.jsp"));
        intent.setData(Uri.parse(urlResult));
        //intent.putExtra("urlpath", "http://computer.kevincrack.com/download.jsp");
        intent.putExtra("urlpath", urlResult);
        startService(intent);
        */
    }
}
