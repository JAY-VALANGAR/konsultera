package com.valangar.konsultera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.valangar.konsultera.LocalDatabase.DatabaseHandler;
import com.valangar.konsultera.LocalDatabase.UserInfo;
import com.valangar.konsultera.Model.UserDetailModel;
import com.valangar.konsultera.Model.UserModel;
import com.valangar.konsultera.ViewModelUser.ViewModelDetailUser;
import com.valangar.konsultera.ViewModelUser.ViewModelUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static String KEY_USERNAME = "key_username", KEY_PASSWORD ="key_password", KEY_ACCESS = "key_access";

    EditText etUsername, etPassword;
    Button btnLogin, btnSecondActivity;

    ViewModelUser viewModelUser;
    ViewModelDetailUser viewModelDetailUser;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUsername(KEY_USERNAME, etUsername.getText().toString().trim(), getApplicationContext());
                setPassword(KEY_PASSWORD, etPassword.getText().toString().trim(), getApplicationContext());
                businessLogic();
            }
        });

        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readAllUserDetails();
            }
        });

    }

    private void businessLogic() {

        Sprite doubleBounce = new DoubleBounce();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminateDrawable(doubleBounce);

        viewModelUser.getUserLoginListObserver().observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel userModelResponseData) {

                if(userModelResponseData != null){
                    Log.i("test_api", "getMessage :- "+userModelResponseData.getMessage());
                    Log.i("test_api", "getAccess :- "+userModelResponseData.getAccess());
                    setAccess(KEY_ACCESS, userModelResponseData.getAccess(), getApplicationContext());
                }else{
                    Log.i("test_api", "Null data");
                }


            }
        });
        viewModelUser.makeApiCall(getApplicationContext());



        viewModelDetailUser.getDetailUSerList().observe(this, new Observer<UserDetailModel>() {
            @Override
            public void onChanged(UserDetailModel userDetailModel) {

                if(userDetailModel != null){

                    deleteAllUser();
                    for(int i = 0; i < userDetailModel.getData().getUser().size(); i++){
                       String user_name = userDetailModel.getData().getUser().get(i);
                        saveUSerDetails(user_name);
                    }

                    Log.i("test_api", "From access getMessage :- "+userDetailModel.getMessage());

                }else{
                    Log.i("test_api", "Null data");
                }


            }
        });
        viewModelDetailUser.makeApiCallForUserDetail(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                readAllUserDetails();
            }
        }, 3000);

    }

    public void saveUSerDetails(String user_name){
        DatabaseHandler db = new DatabaseHandler(this);

        // Inserting Contacts
        Log.i("test_api", "Inserting ..");
        db.addUser(new UserInfo(user_name));
        db.close();
    }


    public void deleteAllUser(){
        DatabaseHandler db = new DatabaseHandler(this);

        // Inserting Contacts
        Log.i("test_api", "Inserting ..");
        db.deleteAllPreviousUser();
        db.close();

    }

    public void readAllUserDetails(){

        Intent i = new Intent(MainActivity.this, UserDetailActivity.class);
        startActivity(i);

//        DatabaseHandler db = new DatabaseHandler(this);
//        // Reading all contacts
//        Log.i("test_api", "Reading all user Details..");
//        List<UserInfo> users = db.getAllUserInfo();
//
//        for (UserInfo userInfo : users) {
//            String log = ""+userInfo.getNames();
//            // Writing Contacts to log
//            Log.d("test_api","Result :- "+ log);
//        }
//        db.close();
    }

    private void initialise() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSecondActivity = findViewById(R.id.btnSecondActivity);

        progressBar = (ProgressBar)findViewById(R.id.spin_kit);

        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelDetailUser = ViewModelProviders.of(this).get(ViewModelDetailUser.class);
    }

    //----------------------------------------------------------------------------------------------
    public static void setUsername(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getUsername(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key,"");
    }


    public static void setPassword(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPassword(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key,"");
    }


    public static void setAccess(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getAccess(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key,"");
    }
    //----------------------------------------------------------------------------------------------

}