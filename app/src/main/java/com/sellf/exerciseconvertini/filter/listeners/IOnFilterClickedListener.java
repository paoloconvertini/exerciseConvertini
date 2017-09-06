package com.sellf.exerciseconvertini.filter.listeners;


import com.sellf.exerciseconvertini.filter.models.SelectableFilters;

/**
 * Created by pconvertini on 20/07/2017.
 */

public interface IOnFilterClickedListener {
    void onFilterClick(SelectableFilters selectableFilters);

    void resetFilters();
}