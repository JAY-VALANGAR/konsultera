package com.valangar.konsultera.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.valangar.konsultera.LocalDatabase.UserInfo;
import com.valangar.konsultera.R;

import java.util.List;


public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.MyViewHolder> {

    private List<UserInfo> userInfoList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_user_name);
            imageView = (ImageView) view.findViewById(R.id.img_profile);

        }
    }


    public UserInfoAdapter(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_user_details, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserInfo userInfo = userInfoList.get(position);
        holder.name.setText(userInfo.getNames());
    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }
}