package com.example.events;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>  {
    Activity activity;
    int sportsAdapterPosition;

    List<Event> eventList = new ArrayList<Event>();

    public EventAdapter(Activity activity, int sportsAdapterPosition){
        this.activity = activity;
        this.sportsAdapterPosition = sportsAdapterPosition;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_holder, parent, false);

        TextView eventTimeTextView = view.findViewById(R.id.textview_eventholder_time);
        ImageButton favoriteImageButton = view.findViewById(R.id.imagebutton_eventholder_favorite);
        TextView firstPlayerTextView = view.findViewById(R.id.textview_eventholder_firstplayer);
        TextView secondPlayerTextView = view.findViewById(R.id.textview_eventholder_secondplayer);

        return new EventAdapter.ViewHolder(view, eventTimeTextView, favoriteImageButton, firstPlayerTextView, secondPlayerTextView);
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        Date date = new java.util.Date(eventList.get(position).getEventStartTime());
        String time = new SimpleDateFormat("hh:mm:ss").format(date);
        holder.eventTimeTextView.setText(time);

        String[] players = eventList.get(position).getPlayers().split(" - ");
        holder.firstPlayerTextView.setText(players[0]);
        holder.secondPlayerTextView.setText(players[1]);

        //favorite
        if(eventList.get(position).isFavorite)
            holder.favoriteImageButton.setColorFilter(Color.YELLOW);
        else
            holder.favoriteImageButton.setColorFilter(Color.BLACK);

        holder.favoriteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFavoritePressed(position, holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(eventList == null || eventList.size() == 0)
            return 0;

        return eventList.size();
    }

    public void updateAdapter(List<Event> newEventList){
        eventList.clear();
        eventList.addAll(newEventList);

        notifyDataSetChanged();
    }

    private void onFavoritePressed(int position, EventAdapter.ViewHolder holder){
        if(eventList.get(position).isFavorite){
            eventList.get(position).isFavorite = false;//TODO

            holder.favoriteImageButton.setColorFilter(Color.BLACK);

            if(position != eventList.size()){
                for(int i=eventList.size()-1;i>position;i--){
                    if(eventList.get(i).isFavorite){
                        //move the unstarred event to the last item of the list
                        Event unstarredEvent = eventList.get(position);
                        eventList.remove(position);
                        eventList.add(unstarredEvent);

                        notifyDataSetChanged();
                        ((MainActivity)activity).sportsAdapter.sportList.get(sportsAdapterPosition).setEventList(eventList);

                        break;
                    }
                }
            }
        }
        else{
            eventList.get(position).isFavorite = true;

            holder.favoriteImageButton.setColorFilter(Color.YELLOW);

            if(position != 0){
                for(int i = 0; i<position; i++){
                    if(!eventList.get(i).isFavorite){
                        //move the starred event to the first item of the list
                        Event starredEvent = eventList.get(position);
                        eventList.remove(position);
                        eventList.add(0, starredEvent);

                        notifyDataSetChanged();
                        ((MainActivity)activity).sportsAdapter.sportList.get(sportsAdapterPosition).setEventList(eventList);

                        break;
                    }
                }
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView eventTimeTextView;
        public ImageButton favoriteImageButton;
        public TextView firstPlayerTextView;
        public TextView secondPlayerTextView;

        public ViewHolder(View view, TextView eventTimeTextView, ImageButton favoriteImageButton, TextView firstPlayerTextView, TextView secondPlayerTextView){
            super(view);

            this.eventTimeTextView = eventTimeTextView;
            this.favoriteImageButton = favoriteImageButton;
            this.firstPlayerTextView = firstPlayerTextView;
            this.secondPlayerTextView = secondPlayerTextView;
        }
    }
}
