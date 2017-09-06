package com.sellf.exerciseconvertini.filter.models;

/**
 * Created by pconvertini on 25/07/2017.
 */

public class SelectableFilter {
    private String name;
    private boolean selected = false;

    public SelectableFilter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
