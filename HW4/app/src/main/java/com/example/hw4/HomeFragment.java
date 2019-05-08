package com.example.hw4;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;





public class HomeFragment extends Fragment
        implements View.OnClickListener{

    public interface OnNextClickListener{
        void OnHomeFragmentNextClick(HomeFragment fragment);
    }

    OnNextClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }



    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            listener = (OnNextClickListener) context;
        } catch (ClassCastException exp) {
            throw  new  ClassCastException((context.toString() +
                    " implement HomeFragementNextClick needed."));
        }
    }

    @Override
    public  void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button nextButton =
                (Button)this.getView().findViewById(R.id.frag_home_btn);
        nextButton.setOnClickListener(this);
    }

    public void onClick(View view){
        if (listener != null){
            listener.OnHomeFragmentNextClick(this);
        }
    }
}