package com.example.hw6;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity
        implements HomeFragment.OnNextClickListener,
            HomeFragment.OnLoadFinishedListener{

    final static String TAG = "MainActivity :=";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.fragment_host, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void DOTCamTransactions(String query){
        // this goes to CamFragment
        CamFragment fragment = (CamFragment)
                getSupportFragmentManager().findFragmentById(R.id.cam_frag);
        fragment.updateDOTCamTrans(query);
    }

    public void swapFragments(Fragment fragment){
        Fragment newFragment = null;

        if (fragment instanceof HomeFragment) {
            /*HomeFragment homeFragment = (HomeFragment) fragment;
            homeFragment.setOnLoadFinishedListener(this);*/

            newFragment = new CamFragment();
        } else {
            newFragment = null;
        }

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_host, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void OnHomeFragmentNextClick(HomeFragment fragment) {
        swapFragments(fragment);
    }

/*    @Override
    public void DOTCamData(String data) {
        Log.e(TAG, data);
    }*/
}

/*
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {

    private final static String TAG = "From MainActivity := ";
    private TextView views;
    private ImageView cameraViews;
    public static final String MESSAGE_ID = "my.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getBtn = findViewById(R.id.button_get);
        getBtn.setOnClickListener(this);
        views = findViewById(R.id.views);
        cameraViews = findViewById(R.id.cameraImage);
    }

    @Override
    public void onClick(View v) {

        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if(info != null && info.isConnected()){
            views.setText(getResources().getString(R.string.awaits));

            Bundle bundle = new Bundle();
            bundle.putString("queryString", "13");
            getSupportLoaderManager().restartLoader(0, bundle, this);
        } else {
            views.setText(getResources().getString(R.string.fails));
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

        camLauncher(s);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void camLauncher(String query){

        Log.d(TAG, "launch camera");
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        intent.putExtra(MESSAGE_ID, query);
        startActivity(intent);

    }
}
*/
