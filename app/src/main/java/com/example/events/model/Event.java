package com.example.events.model;

import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("i")
    public String eventId;

    @SerializedName("si")
    public String sportId;

    @SerializedName("d")
    public String eventName;

    @SerializedName("tt")
    public long eventStartTime;

    public boolean isFavorite = false;

    public long getEventStartTime(){
        return eventStartTime;
    }

    public String getPlayers(){
        return eventName;
    }
}
