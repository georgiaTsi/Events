package com.example.events;

import com.example.events.SportsAdapter;
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
}
