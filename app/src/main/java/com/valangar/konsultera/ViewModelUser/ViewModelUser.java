package com.valangar.konsultera.ViewModelUser;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.valangar.konsultera.MainActivity;
import com.valangar.konsultera.Model.LoginModel;
import com.valangar.konsultera.Model.UserModel;
import com.valangar.konsultera.Network.APIService;
import com.valangar.konsultera.Network.RetroInstance;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelUser extends ViewModel {

    private MutableLiveData<UserModel> userList;

    public ViewModelUser(){
        userList = new MutableLiveData<>();
    }

    public MutableLiveData<UserModel> getMoviesListObserver() {
        return userList;

    }

    public void makeApiCall(Context context) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);

        LoginModel loginModel = new LoginModel(MainActivity.getUsername(MainActivity.KEY_USERNAME, context), MainActivity.getPassword(MainActivity.KEY_PASSWORD, context));

        Call<UserModel> call = apiService.getUsersDetails(loginModel);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                userList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                userList.postValue(null);

                Log.i("test_api", "ERROR :- "+t.getMessage().toString());

            }
        });
    }
}