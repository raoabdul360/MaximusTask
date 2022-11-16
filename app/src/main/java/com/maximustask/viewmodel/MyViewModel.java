package com.maximustask.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.maximustask.listeners.ApiInterface;
import com.maximustask.model.FactModel;
import com.maximustask.network.RetrofitClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyViewModel extends ViewModel {


    public MutableLiveData<FactModel> factModel=new MutableLiveData<>();






    public void loadFact() {

        ApiInterface apiInterface = RetrofitClass.getRetrofitInstance().create(ApiInterface.class);
        Call<FactModel> call = apiInterface.getFact();
        call.enqueue(new Callback<FactModel>() {
            @Override
            public void onResponse(Call<FactModel> call, Response<FactModel> response) {


                factModel.setValue( response.body());


            }

            @Override
            public void onFailure(Call<FactModel> call, Throwable t) {
                Log.e("retrofit", "call failed");

            }

        });
    }
}

