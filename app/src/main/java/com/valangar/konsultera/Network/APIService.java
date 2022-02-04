package com.valangar.konsultera.Network;

import com.valangar.konsultera.Model.LoginModel;
import com.valangar.konsultera.Model.UserModel;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;



public interface APIService {

    @POST(".")
    Call<UserModel> getUsersDetails(@Body LoginModel loginModel);
}
