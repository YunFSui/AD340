package com.example.hw3;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LegoKitRecyclerAdapter
        extends  RecyclerView.Adapter<LegoKitRecyclerAdapter.ViewHolder> {

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onClick(int position);
    }

    private LegoKit[] kits;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView layout;

        public ViewHolder(CardView v) {
            super(v);
            layout = v;
        }
    }

    public LegoKitRecyclerAdapter(LegoKit[] kits) { this.kits = kits; }

    @Override
    public int getItemCount() { return kits.length; }

    @Override
    public LegoKitRecyclerAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_recyclerview_item, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cardView = holder.layout;
        TextView title = (TextView)cardView.findViewById(R.id.title);
        TextView year =  (TextView)cardView.findViewById(R.id.year);

        Context context = cardView.getContext();

        LegoKit kit = kits[position];
        String tit = kit.getTitle();
        String url = kit.getUri();


        title.setText(tit);
        year.setText("Year: "
                 + kit.getYear());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener !=null) {
                    listener.onClick(position);
                }
            }
        });

    }
}
