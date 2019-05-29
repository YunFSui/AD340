package com.example.hw6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.util.Log;

import static com.example.hw6.MainActivity.MESSAGE_ID;


public class RecyclerViewActivity extends AppCompatActivity {

    final static String TAG = "RecyclerView := ";
    private DOTCams[] cams;

    @Override
    protected void onCreate(Bundle savedInstantceState) {

        super.onCreate(savedInstantceState);
        setContentView(R.layout.activity_recyclerview);

        Intent intent = getIntent();
        String data = intent.getStringExtra(MESSAGE_ID);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        this.cams = DOTCams.initialize_cam(data);
        DOTCamRecyclerAdapter adapter = new DOTCamRecyclerAdapter(cams);
        recyclerView.setAdapter(adapter);
    }
}
