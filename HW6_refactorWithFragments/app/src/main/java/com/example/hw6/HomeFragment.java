package com.example.hw6;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class HomeFragment extends Fragment
        implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {

    private final static String TAG = "HomeFragment := ";
    private TextView views;
    private ImageView cameraViews;
    private OnLoadFinishedListener mCallback;

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = "";
        if (bundle != null){
            queryString = bundle.getString("queryString");
        }

        Log.e(TAG, queryString);
        return new DOTAsyncTaskLoader(getActivity(), queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        // Log.e(TAG, "onLoadFinished"+data);

        mCallback.DOTCamTransactions(data);

        /*callback.DOTCamData(data);*/

        /*onLoad_listener.DOTCamData(data);*/
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


    public  interface OnNextClickListener{
        void OnHomeFragmentNextClick(HomeFragment fragment);
    }

/*    public void setOnLoadFinishedListener (OnLoadFinishedListener callback){
        this.callback = callback;
    }*/

    public interface OnLoadFinishedListener{
        void DOTCamTransactions(String data);
    }

    OnNextClickListener listener;
    /*OnLoadFinishedListener onLoad_listener;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try{
            listener = (OnNextClickListener) context;
        } catch (ClassCastException exp) {
            throw new ClassCastException(context.toString() +
                    " implement HomeFragmentNextClick needed.");
        }

        try{
            mCallback = (OnLoadFinishedListener) context;
        } catch (ClassCastException exp) {
            throw new ClassCastException( context.toString() +
                    " implements DOTCamTransactions needed.");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Button nextBtn =
                (Button)this.getView().findViewById(R.id.cam_btn);
        nextBtn.setOnClickListener(this);
        views = getActivity().findViewById(R.id.views);
        cameraViews = getActivity().findViewById(R.id.camera_image);
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.OnHomeFragmentNextClick(this);
        }

        ConnectivityManager manager =
                (ConnectivityManager) getActivity().
                        getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if(info != null && info.isConnected()){
            views.setText(getResources().getString(R.string.awaits));

            Bundle bundle = new Bundle();
            bundle.putString("queryString", "13");
            getActivity().getSupportLoaderManager()
                    .restartLoader(0, bundle, this);
        } else {
            views.setText(getResources().getString(R.string.fails));
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

}
