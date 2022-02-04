package com.valangar.konsultera.LocalDatabase;

public class UserInfo {

    String names;


    public UserInfo(){

    }

    public UserInfo(String name){
        this.names = name;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
