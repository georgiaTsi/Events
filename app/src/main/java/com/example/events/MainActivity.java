package com.example.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSportsRecyclerView();
    }

    private void initSportsRecyclerView(){
        RecyclerView sportsRecyclerView = findViewById(R.id.recyclerview_main_sports);

        sportsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        SportsAdapter sportsAdapter = new SportsAdapter(this);
        sportsRecyclerView.setAdapter(sportsAdapter);

        getSports(sportsAdapter);
    }

    private void getSports(SportsAdapter sportsAdapter){
        Call<List<Sport>> call = RetrofitClient.getInstance().getMyApi().getSports();
        call.enqueue(new Callback<List<Sport>>() {
            @Override
            public void onResponse(Call<List<Sport>> call, Response<List<Sport>> response) {
                List<Sport> results = response.body();

                sportsAdapter.updateAdapter(results);
            }

            @Override
            public void onFailure(Call<List<Sport>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
}