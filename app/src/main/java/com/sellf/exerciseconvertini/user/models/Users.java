package com.sellf.exerciseconvertini.user.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pconvertini on 05/09/2017.
 */
public class Users implements Serializable
{

    @SerializedName("meta")
    @Expose
    public Meta meta;
    @SerializedName("data")
    @Expose
    public List<User> userList = null;

    public List<User> getUserList() {
        return userList;
    }
}
