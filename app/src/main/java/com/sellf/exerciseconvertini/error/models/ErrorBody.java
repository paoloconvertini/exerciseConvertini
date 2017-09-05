package com.sellf.exerciseconvertini.error.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by pconvertini on 05/09/2017.
 */
public class ErrorBody implements Serializable
{

    @SerializedName("error")
    @Expose
    public Error error;

    public ErrorBody(String error) {
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}