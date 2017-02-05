package com.hackbrown.bharatvaidhyanathan.collabalarmnew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Hello 1");
        setContentView(R.layout.activity_main);
        System.out.println("Hello 2");
        Intent i = new Intent(this, AlarmActivity.class);
        System.out.println("Hello 3");
        startActivity(i);
        System.out.println("Hello 4");
    }
}
