package com.example.hw2;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity  extends AppCompatActivity  {
    private static  final String TAG = "Message from 1st Act: ";

    @Override
    protected void onCreate(Bundle savedState) {

        super.onCreate(savedState);

        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE_ID);

        TextView label = (TextView)findViewById(R.id.intent_message);
        label.setText(message);

        Log.i(TAG, this.getLifecycle()
                .getCurrentState()
                .toString());
    }



}
