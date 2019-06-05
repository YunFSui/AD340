package com.example.hw7;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    private final static String TAG = "From MainActivity := ";
    public static final String MESSAGE_ID = "camera.message";

    private DOTCams[] cams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if(info != null && info.isConnected()){

            Bundle bundle = new Bundle();
            bundle.putString("queryString", "13");
            getSupportLoaderManager().restartLoader(0, bundle, this);
            Toast toast = Toast.makeText(
                    this, getResources().getString(R.string.awaits)
                    ,Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(
                    this, getResources().getString(R.string.fails)
                    ,Toast.LENGTH_SHORT);
            toast.show();
        }

    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = "";
        if (bundle != null){
            queryString = bundle.getString("queryString");
        }

        Log.e(TAG, queryString);
        return new DOTAsyncTaskLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        Log.e(TAG, s);

        recyclerViewsLauncher(s);
    }

    public void recyclerViewsLauncher(String query){

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        this.cams = DOTCams.initialize_cam(query);
        DOTCamRecyclerAdapter adapter = new DOTCamRecyclerAdapter(cams);
        recyclerView.setAdapter(adapter);

        final Intent intentLocToMap = new Intent(this, MapActivity.class);

        adapter.setListener(new DOTCamRecyclerAdapter.Listener() {
            @Override
            public void onClick(int position) {
                String[] camPak = new String[4];
                camPak[0] = Double.toString(cams[position].getLat());
                camPak[1] = Double.toString(cams[position].getLng());
                camPak[2] = cams[position].getView();
                camPak[3] = cams[position].getImageUrl();
                Log.i(TAG, "sending: " + camPak);
                intentLocToMap.putExtra(MESSAGE_ID, camPak);
                startActivity(intentLocToMap);
            }
        });

    }



    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
