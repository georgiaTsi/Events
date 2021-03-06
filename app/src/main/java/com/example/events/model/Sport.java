package com.example.events.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sport {

    @SerializedName("i")
    private String sportId;

    @SerializedName("d")
    private String sportName;

    @SerializedName("e")
    private List<Event> eventList;

    public String getSportName(){
        return sportName;
    }

    public List<Event> getEventList(){
        return eventList;
    }
    public void setEventList(List<Event> newEventList){
        eventList = newEventList;
    }
}
