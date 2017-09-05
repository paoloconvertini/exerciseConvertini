package com.sellf.exerciseconvertini.error.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by pconvertini on 05/09/2017.
 */

public class Error implements Serializable
{

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("details")
    @Expose
    public Details details;

    public Details getDetails() {
        return details;
    }
}