package com.example.hw6;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.ArrayAdapter;

public class DOTCameraAdoptor extends ArrayAdapter<DOTCams> {

    private final Context context;
    private final DOTCams[] cams;

    public DOTCameraAdoptor(Context context, DOTCams[] vals){
        super(context, -1, vals);
        this.cams = vals;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView view = new TextView(context);
        view.setText(cams[position].getView());

        TextView url = new TextView(context);
        url.setText(cams[position].getImageUrl());

        layout.addView(view);
        layout.addView(url);
        return layout;
    }


}
