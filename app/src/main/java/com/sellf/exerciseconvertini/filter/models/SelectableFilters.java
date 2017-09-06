package com.sellf.exerciseconvertini.filter.models;

import java.io.Serializable;

/**
 * Created by pconvertini on 24/07/2017.
 */

public class SelectableFilters implements Serializable{
    private String name;
    private String lastName;
    private String sorting;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }
}
