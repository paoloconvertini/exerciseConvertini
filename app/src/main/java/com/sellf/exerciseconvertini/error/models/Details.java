package com.sellf.exerciseconvertini.error.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by pconvertini on 05/09/2017.
 */

public class Details implements Serializable
{

    @SerializedName("email")
    @Expose
    public String email;

    public String getEmail() {
        return email;
    }
}
