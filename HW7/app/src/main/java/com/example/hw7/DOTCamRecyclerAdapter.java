package com.example.hw7;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DOTCamRecyclerAdapter
        extends RecyclerView.Adapter<DOTCamRecyclerAdapter.ViewContainer> {

    private DOTCams[] cams;
    private Listener listener;

    interface Listener{
        void onClick(int position);
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    public static class ViewContainer extends RecyclerView.ViewHolder {
        private CardView layout;

        public ViewContainer(CardView v){
            super(v);
            layout = v;
        }
    }

    public DOTCamRecyclerAdapter(DOTCams[] cams){
        this.cams = cams;
    }

    @Override
    public int getItemCount(){
        return cams.length;
    }

    @Override
    public DOTCamRecyclerAdapter.ViewContainer onCreateViewHolder(
            ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_recyclerview, parent, false);
        return new ViewContainer(cardView);
    }

    @Override
    public void onBindViewHolder(ViewContainer container, final int positon){
        CardView cardView = container.layout;
        TextView view = cardView.findViewById(R.id.views);
        ImageView img = cardView.findViewById(R.id.cameraImage);

        DOTCams cam = cams[positon];
        view.setText(cam.getView());

        Picasso.get().load(cam.getImageUrl()).into(img);

        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener != null){
                    listener.onClick(positon);
                }
            }
        });
    }
}
