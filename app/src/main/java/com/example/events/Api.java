package com.example.events;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://618d3aa7fe09aa001744060a.mockapi.io/api/";

    @GET("sports")
    Call<List<Sport>> getSports();
}
