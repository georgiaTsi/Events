package com.example.events.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.events.R;
import com.example.events.model.Sport;
import com.example.events.adapter.SportsAdapter;
import com.example.events.model.Sport;
import com.example.events.presenter.MainPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;

    public SportsAdapter sportsAdapter;
    private RecyclerView sportsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter();
        presenter.attachView(this);

        presenter.getSports();
    }

    @Override
    public void displaySports(List<Sport> sports){
        sportsRecyclerView = findViewById(R.id.recyclerview_main_sports);

        sportsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sportsAdapter = new SportsAdapter(this);
        sportsRecyclerView.setAdapter(sportsAdapter);

        sportsAdapter.updateAdapter(sports);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}