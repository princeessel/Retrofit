package com.example.consultants.retrofit.api;

import com.example.consultants.retrofit.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface RandomAPI {



    // base_url/ + api

    // https://randomuser.me/api

    @GET("api")

    Call<UserResponse> getRandomUser();



    // https://randomuser.me/api?results={count}

    @GET("api")

    Call<UserResponse> getRandomUsers(@Query("results") int count);



    // https://randomuser.me/api/user/{id}

    // for show

    @GET("api/user")

    Call<UserResponse> getUser(@Path("id") String id);







}