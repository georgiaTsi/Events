package com.example.events;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {

    Activity activity;

    List<Sport> sportList = new ArrayList<Sport>();

    public SportsAdapter(Activity activity){
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sport_holder, parent, false);

        LinearLayout sportTitle = view.findViewById(R.id.linearlayout_sportholder_title);
        TextView sportNameTextView = view.findViewById(R.id.textview_sportholder_sportname);
        ImageView arrowImageView = view.findViewById(R.id.imageview_sportholder_arrow);
        RecyclerView eventsRecyclerView = view.findViewById(R.id.recyclerview_sportholder_events);

        return new ViewHolder(view, sportTitle, sportNameTextView, arrowImageView, eventsRecyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.sportNameTextView.setText(sportList.get(position).getSportName().toUpperCase());

        //RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        holder.eventsRecyclerView.setLayoutManager(layoutManager);

        EventAdapter eventsAdapter = new EventAdapter(activity, position);
        holder.eventsRecyclerView.setAdapter(eventsAdapter);

        eventsAdapter.updateAdapter(sportList.get(position).getEventList());

        //title
        onExpandPressed(holder);

        holder.sportTitleLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.isSportExpanded = !holder.isSportExpanded;

                onExpandPressed(holder);
            }
        });
    }

    private void onExpandPressed(ViewHolder holder) {
        if(!holder.isSportExpanded){//close
            holder.eventsRecyclerView.setVisibility(View.GONE);
            holder.arrowImageView.setRotation(0);
        }
        else{//expand
            holder.eventsRecyclerView.setVisibility(View.VISIBLE);
            holder.arrowImageView.setRotation(180);
        }
    }

    @Override
    public int getItemCount() {
        if(sportList == null || sportList.size() == 0)
            return 0;

        return sportList.size();
    }

    public void updateAdapter(List<Sport> newSportList){
        sportList.clear();
        sportList.addAll(newSportList);

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout sportTitleLinearLayout;
        public TextView sportNameTextView;
        public ImageView arrowImageView;
        public RecyclerView eventsRecyclerView;

        public boolean isSportExpanded = true;

        public ViewHolder(View view, LinearLayout sportTitleLinearLayout, TextView sportNameTextView, ImageView arrowImageView, RecyclerView eventsRecyclerView){
            super(view);

            this.sportTitleLinearLayout = sportTitleLinearLayout;
            this.sportNameTextView = sportNameTextView;
            this.arrowImageView = arrowImageView;
            this.eventsRecyclerView = eventsRecyclerView;
        }
    }
}
