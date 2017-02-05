package com.hackbrown.bharatvaidhyanathan.collabalarmnew;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.provider.AlarmClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AlarmActivity extends AppCompatActivity {
    private ListView listView1;
    private AlarmAdapter adapter;
    private TextView timeText;
    private Intent newAlarmIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);


        ArrayList<Alarm> alarm_data = Globals.getInstance().getAlarms();

        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());

        timeText = (TextView) findViewById(R.id.textView);
        timeText.setText(currentDateTimeString);

        adapter = new AlarmAdapter(this,
                R.layout.listview_row, alarm_data);


        listView1 = (ListView) findViewById(R.id.listview);

//        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
//        listView1.addHeaderView(header);

        listView1.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newAlarmIntent = new Intent(AlarmActivity.this, NewAlarm.class);
                Thread t = new SearchThread(AlarmActivity.this);
                t.start();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private class SearchThread extends Thread {

        private Context context;

        public SearchThread(Context context) {
            this.context = context;
        }

        @Override
        public void run() {

            //Thread.sleep(time);

            URL url = null;
            try {
                url = new URL("http://138.16.49.170:8080/signup?name=%22Bharat%22&username=%22bharatv%22&password=%22abc123%22");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String str;
                while( (str = br.readLine()) != null){
                    System.out.println("Str : "+str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
                handler.sendEmptyMessage(0);
            }

        }

        private Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                //displaySearchResults(search);
                context.startActivity(newAlarmIntent);
            }
        };
    }

}





