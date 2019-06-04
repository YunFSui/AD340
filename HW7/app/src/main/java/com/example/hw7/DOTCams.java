package com.example.hw7;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DOTCams {

    private final static String TAG = "DOT Cameras := ";
    private String imageUrl;
    private String view;

    public DOTCams(String view){
        this.view = view;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public String getView() {
        return view;
    }

    public static class Constructor{
        DOTCams cam;

        public Constructor(){
            cam = new DOTCams("");
        }

        public Constructor view(String view){
            cam.view = view;
            return this;
        }

        public Constructor imageUrl(String imageUrl){
            cam.imageUrl = imageUrl;
            return this;
        }

        public DOTCams setDOTCam(){
            return cam;
        }
    }

    private static String setImageUrl(String type, String imageUrl){
        String url;

        final String SDOT = "http://www.seattle.gov/trafficcams/images/";
        final String WSDOT = "http://images.wsdot.wa.gov/nw/";

        if(type.equalsIgnoreCase("sdot")){
            url = SDOT + imageUrl;
        } else {
            url = WSDOT + imageUrl;
        }
        Log.i(TAG, url);

        return url;
    }

    public static DOTCams[] initialize_cam(String s){

        ArrayList<DOTCams> camArrayList = new ArrayList<>();
        String imgUrl;
        String full_url = "";
        String type;
        String description = "";

        JSONObject root = null;
        try {
            root = new JSONObject(s);
            JSONArray results = root.getJSONArray("Features");

            for(int i = 0; i < results.length(); i++){
                JSONObject first = results.getJSONObject(i);
                JSONArray cameras = first.getJSONArray("Cameras");
                JSONObject prop = cameras.getJSONObject(0);
                imgUrl = prop.getString("ImageUrl");
                description = prop.getString("Description");
                type = prop.getString("Type");

                full_url = setImageUrl(type, imgUrl);

                camArrayList.add(new DOTCams.Constructor().view(description)
                        .imageUrl(full_url)
                        .setDOTCam());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        DOTCams[] cams = new DOTCams[camArrayList.size()];
        cams = camArrayList.toArray(cams);
        return cams;
    }


}