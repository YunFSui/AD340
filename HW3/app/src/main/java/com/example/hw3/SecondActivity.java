package com.example.hw3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.util.Log;


public class SecondActivity extends AppCompatActivity {

    final static  String TAG = "From SecondActivity - ";
    final static String MESSAGE_ID = "my.message";

    private LegoKit[] kits;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        RecyclerView recyclerView =
                (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        this.kits = LegoKit.initKits();
        LegoKitRecyclerAdapter adapter = new LegoKitRecyclerAdapter(kits);
        recyclerView.setAdapter(adapter);

        final Intent intentToDetail = new Intent(this, ThirdActivity.class);

        adapter.setListener(new LegoKitRecyclerAdapter.Listener() {
            @Override
            public void onClick(int position) {

                String pos = Integer.toString(position);
                intentToDetail.putExtra(MESSAGE_ID, pos);
                Log.i(TAG, "To " + position + " " + kits[position].getTitle());
                startActivity(intentToDetail);
            }
        });


    }
}
