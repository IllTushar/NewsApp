package com.example.testproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.testproject.Adapter.RecyclerViewAdapter;
import com.example.testproject.Api.ApiServices;
import com.example.testproject.Api.RetrofitClient;
import com.example.testproject.Model.ResponseModel;
import com.example.testproject.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity
{
private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView =  findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        processData();
    }
    public void processData(){
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Please wait..");
        pd.show();
        ApiServices api = new RetrofitClient().getRetrofit().create(ApiServices.class);
        Call<ResponseModel>call = api.getData();
        try {
            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    if (response.isSuccessful()){
                        pd.dismiss();
                        ResponseModel responseModel = response.body();
                        ArrayList<ResponseModel.Data>responseData = responseModel.articles;
                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(responseData);
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(HomeActivity.this, "Some thing went wrong !!", Toast.LENGTH_SHORT).show();
                pd.dismiss();
                }
            });

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}