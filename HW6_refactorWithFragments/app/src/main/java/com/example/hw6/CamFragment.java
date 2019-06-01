package com.example.hw6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CamFragment extends Fragment {

    private final static String TAG = "CamFragment := ";
    private String data;

    DOTCams[] cams;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cam, container, false);

/*        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager manager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(manager);

        this.cams = DOTCams.initialize_cam(data);
        DOTCamRecyclerAdapter adapter = new DOTCamRecyclerAdapter(cams);
        recyclerView.setAdapter(adapter);*/

        Log.i(TAG, "this " + data);

        return view;
    }


    public void updateDOTCamTrans(String query){
        Log.i(TAG, query);
        data = query;
        /*return query;*/
    }
}
