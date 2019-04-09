package com.example.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView content = new TextView(this);


        String name = "Yun F. Sui";
        String techInterests
                = "I am interested in Aviation related IT files"
                + "(aka. automation, data analysis and visulizaion, controls, infurstructure,"
                + " and VR applications.)";
        content.setText(name + "\n" + techInterests);

        setContentView(content);
    }
}
