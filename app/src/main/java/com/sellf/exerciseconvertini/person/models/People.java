package com.sellf.exerciseconvertini.person.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pconvertini on 04/09/2017.
 */

public class People implements Serializable {
    @SerializedName("meta")
    @Expose
    public Meta meta;
    @SerializedName("data")
    @Expose
    public List<Person> peopleList = new ArrayList<>();

    public List<Person> getPeopleList() {
        return peopleList;
    }
}
