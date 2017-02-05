package com.hackbrown.bharatvaidhyanathan.collabalarmnew;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AlarmActivity extends AppCompatActivity {
    private ListView listView1;
    private AlarmAdapter adapter;
    private TextView timeText;

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
                Intent i = new Intent(AlarmActivity.this, NewAlarm.class);
                startActivity(i);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }



}




