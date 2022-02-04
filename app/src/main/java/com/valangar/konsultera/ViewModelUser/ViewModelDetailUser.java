package com.valangar.konsultera.ViewModelUser;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.valangar.konsultera.View.MainActivity;
import com.valangar.konsultera.Model.UserDetailModel;
import com.valangar.konsultera.Network.APIService;
import com.valangar.konsultera.Network.RetroInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewModelDetailUser extends ViewModel {

    private MutableLiveData<UserDetailModel> userList;

    public ViewModelDetailUser(){
        userList = new MutableLiveData<>();
    }

    public MutableLiveData<UserDetailModel> getDetailUSerList() {
        return userList;

    }

    public void makeApiCallForUserDetail(Context context) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);

        String access = MainActivity.getAccess(MainActivity.KEY_ACCESS, context);

        Call<UserDetailModel> call = apiService.getUserDetails(access);

        call.enqueue(new Callback<UserDetailModel>() {
            @Override
            public void onResponse(Call<UserDetailModel> call, Response<UserDetailModel> response) {
                userList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UserDetailModel> call, Throwable t) {
                userList.postValue(null);

                Log.i("test_api", "ERROR :- "+t.getMessage().toString());

            }
        });
    }
}
