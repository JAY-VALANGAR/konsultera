package com.valangar.konsultera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserDetailModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataModel dataModel;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataModel getData() {
        return dataModel;
    }

    public void setData(DataModel dataModel) {
        this.dataModel = dataModel;
    }

}
