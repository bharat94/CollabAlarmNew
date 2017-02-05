package com.hackbrown.bharatvaidhyanathan.collabalarmnew;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by vaidhyanathannarayanan on 05/02/17.
 */

public class NewAlarm extends AppCompatActivity{

    private ListView listView1;
    private Button b1;
    private EditText et;
    private TimePicker tp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);

        CheckedTextView c1 = new CheckedTextView(NewAlarm.this);
        CheckedTextView c2 = new CheckedTextView(NewAlarm.this);

        c1.setText("Rohan");
        c1.setChecked(true);
        c2.setText("Akhila");

        CheckedTextView list_data[] = new CheckedTextView[]{c1, c2};

        CheckedAdapter adapter = new CheckedAdapter(this,
                R.layout.checklistview_row, list_data);


        listView1 = (ListView) findViewById(R.id.listview1);

//        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
//        listView1.addHeaderView(header);

        listView1.setAdapter(adapter);
        listView1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view,int position,long id) {
                View v = listView1.getChildAt(position);
                CheckedTextView ctv = (CheckedTextView) v.findViewById(R.id.ctv);
                ctv.toggle();
            }
        });

        et = (EditText) findViewById(R.id.textAdd);
        tp = (TimePicker) findViewById(R.id.timePicker);


        b1 = (Button) findViewById(R.id.buttonAdd);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String imageName = "%2d" +":"+ "%2d";

                String str = String.format( imageName, tp.getCurrentHour().intValue(), tp.getCurrentMinute().intValue() );
                System.out.println(str);

                Globals.getInstance().addToAlarms(new Alarm(str, et.getText().toString(), true));

                Intent openNewAlarm = new Intent(AlarmClock.ACTION_SET_ALARM);
                openNewAlarm.putExtra(AlarmClock.EXTRA_HOUR, tp.getCurrentHour().intValue());
                openNewAlarm.putExtra(AlarmClock.EXTRA_MINUTES, tp.getCurrentMinute().intValue());
                startActivity(openNewAlarm);


            }
        });
    }



    public long getTimeInMillis(int h, int m){
        long l = h*60 + m;
        l = l * 60 * 1000;
        System.out.println("val : "+l);
        return l;
    }



}
