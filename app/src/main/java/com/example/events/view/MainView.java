package com.example.events.view;

import com.example.events.model.Sport;

import java.util.List;

public interface MainView {
    void displaySports(List<Sport> results);

    void showMessage(String message);
}
