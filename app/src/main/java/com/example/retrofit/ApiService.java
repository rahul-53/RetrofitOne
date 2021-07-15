package com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/api/users/{userId}")
    Call<ResponseDTO> getUser(@Path("userId") int userId);
}
