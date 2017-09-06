package com.sellf.exerciseconvertini.user.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pconvertini on 05/09/2017.
 */

public class User {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("email")
        @Expose
        public String email;

        public int getId() {
                return id;
        }
}
