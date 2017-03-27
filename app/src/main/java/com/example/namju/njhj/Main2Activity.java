package com.example.namju.njhj;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    long mStartTime, mEndTime;
    EpubXMLParser mXMLParser;

    private TextToSpeech mTTS;
    static List<File> epubs;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;
    static File selected;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTTS = new TextToSpeech(getApplicationContext(), (TextToSpeech.OnInitListener) this);

        listview = (ListView)findViewById(R.id.booklist);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //selected = epubs.get(position);
                //Intent resultIntent = new Intent();
                //resultIntent.putExtra("bpath", selected.getAbsolutePath());
                //setResult(Activity.RESULT_OK, resultIntent);

                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("EPUBNAME", ""+list.get(position));
                startActivity(intent);

            }
        });

        mStartTime = System.currentTimeMillis();
        mXMLParser = new EpubXMLParser("http://computer.kevincrack.com/epub_download.jsp", mHandler);
        Thread thread = new Thread(mXMLParser);
        thread.start();

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mEndTime = System.currentTimeMillis();
            Log.d("Taken Time", Long.toString((mEndTime - mStartTime) / 1000L));

            ArrayList<EpubDatas> dataList = mXMLParser.getResult();
            int dataListSize = dataList.size();
            Log.d("Data List Size", Integer.toString(dataListSize));
            for (int i=0; i<dataListSize; i++) {
                Log.d("XML Parsing Result", dataList.get(i).getEpub());
                list.add(dataList.get(i).getEpub());         //서버에서 불러온 전자책들을 리스트로 보여줌
            }
        }
    };

    public void onInit(int i){
        mTTS.speak("전자책 목록 화면입니다. 원하는 전자책을 선택해주세요.", TextToSpeech.QUEUE_FLUSH, null);
    }
}