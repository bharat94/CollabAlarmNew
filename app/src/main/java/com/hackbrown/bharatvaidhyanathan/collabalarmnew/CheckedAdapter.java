package com.hackbrown.bharatvaidhyanathan.collabalarmnew;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

/**
 * Created by vaidhyanathannarayanan on 05/02/17.
 */

public class CheckedAdapter extends ArrayAdapter<CheckedTextView>{

    Context context;
    int layoutResourceId;
    CheckedTextView data[] = null;

    public CheckedAdapter(Context context, int layoutResourceId, CheckedTextView[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CheckedTextView holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = (CheckedTextView)row.findViewById(R.id.ctv);

            row.setTag(holder);
        }
        else
        {
            holder = (CheckedTextView)row.getTag();
        }

        CheckedTextView ctv = this.data[position];
        holder.setText(ctv.getText());
        holder.setChecked(ctv.isChecked());

        return row;
    }



}
