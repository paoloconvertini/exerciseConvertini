package com.sellf.exerciseconvertini.person.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by pconvertini on 04/09/2017.
 */

public class Meta implements Serializable {

    @SerializedName("has_more")
    @Expose
    public Boolean hasMore;
    @SerializedName("object")
    @Expose
    public String object;
}
