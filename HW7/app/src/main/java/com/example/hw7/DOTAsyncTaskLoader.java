package com.example.hw7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class DOTAsyncTaskLoader extends AsyncTaskLoader<String> {
    public DOTAsyncTaskLoader(@NonNull Context context, String queryString) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {

        String baseURL =
                "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";

        return NetworkConnection.getData(baseURL);
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }
}
