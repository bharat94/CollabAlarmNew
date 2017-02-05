package com.hackbrown.bharatvaidhyanathan.collabalarmnew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vaidhyanathannarayanan on 05/02/17.
 */

public class AlarmAdapter extends ArrayAdapter<Alarm>{

    Context context;
    int layoutResourceId;
    ArrayList<Alarm> data = null;

    public AlarmAdapter(Context context, int layoutResourceId, ArrayList<Alarm> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AlarmHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new AlarmHolder();
            holder.txtName = (TextView)row.findViewById(R.id.name);
            holder.txtTime = (TextView)row.findViewById(R.id.time);
            holder.sw = (Switch)row.findViewById(R.id.switch1);

            row.setTag(holder);
        }
        else
        {
            holder = (AlarmHolder)row.getTag();
        }

        Alarm weather = (Alarm) data.get(position);
        holder.txtName.setText(weather.alarm_name);
        holder.txtTime.setText(weather.alarm_time);
        holder.sw.setChecked(weather.isSelected);
        holder.sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alarm a = Globals.getInstance().getAlarm(position);
                a.isSelected = !a.isSelected;
                toggle(a);
                Globals.getInstance().setAlarm(position,a);
            }
        });

        return row;
    }

    static class AlarmHolder
    {
        TextView txtName;
        TextView txtTime;
        Switch sw;
    }


    public void toggle(Alarm a){
        if(a.isSelected)
            setAlarm(a);
        else
            cancelAlarm(a);

    }

    public void setAlarm(Alarm a){
        String time[] = a.alarm_time.split(":");
        System.out.println("#"+time[0]+time[1]+"#");
        int hour = Integer.parseInt(time[0].trim());
        int min = Integer.parseInt(time[1].trim());

        Intent openNewAlarm = new Intent(AlarmClock.ACTION_SET_ALARM);
        openNewAlarm.putExtra(AlarmClock.EXTRA_HOUR, hour);
        openNewAlarm.putExtra(AlarmClock.EXTRA_MINUTES, min);
        context.startActivity(openNewAlarm);
    }

    public void cancelAlarm(Alarm a) {

        String time[] = a.alarm_time.split(":");
        System.out.println("#"+time[0]+time[1]+"#");
        int hour = Integer.parseInt(time[0].trim());
        int min = Integer.parseInt(time[1].trim());

        System.out.println("hour : "+hour + ","+min);

        Intent intent = new Intent(AlarmClock.ACTION_DISMISS_ALARM);
        setParams(hour, min, intent);
        context.startActivity(intent);
    }

    public void setParams(int hour, int minute, Intent intent) {
        intent.putExtra(AlarmClock.EXTRA_ALARM_SEARCH_MODE, AlarmClock.ALARM_SEARCH_MODE_TIME);
        intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, minute);
    }
}
