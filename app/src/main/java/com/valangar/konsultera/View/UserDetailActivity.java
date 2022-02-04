package com.valangar.konsultera.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.valangar.konsultera.Adapter.UserInfoAdapter;
import com.valangar.konsultera.LocalDatabase.DatabaseHandler;
import com.valangar.konsultera.LocalDatabase.UserInfo;
import com.valangar.konsultera.R;

import java.util.ArrayList;
import java.util.List;

public class UserDetailActivity extends AppCompatActivity {

    private List<UserInfo> userInfoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserInfoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        DatabaseHandler db = new DatabaseHandler(this);
        userInfoList = db.getAllUserInfo();

        mAdapter = new UserInfoAdapter(userInfoList, UserDetailActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}