package com.example.hw2;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity  extends AppCompatActivity  {
    private static  final String TAG = "Message from 1st Act: ";
    private static  final String CON = "Message from 2nd Act: ";

    @Override
    protected void onCreate(Bundle savedState) {

        super.onCreate(savedState);

        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE_ID);

        TextView label = (TextView)findViewById(R.id.intent_message);
        label.setText(message);

        if (message != null){
            Log.i(TAG, message);
            Log.i(CON, this.getLifecycle()
                    .getCurrentState()
                    .toString());
        } else {
            Log.i(CON, "There is no message.");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(CON, "started");
        Log.i(CON, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(CON, "I am back!");
        Log.i(CON, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(CON, "Let's take a break.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(CON, "Halt your horse!");
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        Log.i(CON, "I will be back (Thumb up)");
    }
}
