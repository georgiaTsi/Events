package com.example.events.presenter;

import android.widget.Toast;

import com.example.events.model.RetrofitClient;
import com.example.events.model.Sport;
import com.example.events.view.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements Presenter<MainView> {

    MainView mainView;

    @Override
    public void attachView(MainView view) {
        this.mainView = view;
    }

    @Override
    public void detachView() {
        this.mainView = null;
    }

    public void getSports(){
        Call<List<Sport>> call = RetrofitClient.getInstance().getMyApi().getSports();
        call.enqueue(new Callback<List<Sport>>() {
            @Override
            public void onResponse(Call<List<Sport>> call, Response<List<Sport>> response) {
                List<Sport> results = response.body();

                mainView.displaySports(results);
            }

            @Override
            public void onFailure(Call<List<Sport>> call, Throwable t) {
                mainView.showMessage("An error has occured");
            }
        });
    }
}
