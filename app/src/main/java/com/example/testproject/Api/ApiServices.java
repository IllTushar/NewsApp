package com.example.testproject.Api;

import com.example.testproject.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices
{
  @GET("everything?q=tesla&from=2022-10-11&sortBy=publishedAt&apiKey=e749aabb5b5e4928a8c4cbd4c6885a30")
    Call<ResponseModel>getData();
}
