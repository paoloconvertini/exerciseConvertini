package com.sellf.exerciseconvertini.filter.transformers;

import com.sellf.exerciseconvertini.filter.models.Filters;
import com.sellf.exerciseconvertini.filter.models.SelectableFilter;
import com.sellf.exerciseconvertini.filter.models.SelectableFilters;

import java.util.ArrayList;

/**
 * Created by pconvertini on 25/07/2017.
 */

public class SelectableFiltersToFiltersTransformer {
    private SelectableFilters selectableFilters; //input
    private Filters filters = new Filters(); //output

    public SelectableFiltersToFiltersTransformer(SelectableFilters selectableFilters) {
        this.selectableFilters = selectableFilters;
    }

    public Filters transform() {
        this.transformFilterLastName();
        this.transformFilterName();
        this.transformSortBy();
        return filters;
    }

    private void transformSortBy() {
       this.filters.setSorting(transformSorting(selectableFilters));
    }

    private void transformFilterName() {
        this.filters.setName(transformName(selectableFilters));
    }

    private void transformFilterLastName() {
        this.filters.setLastName(transformLastName(selectableFilters));
    }


    private String transformSorting(SelectableFilters selectableFilters) {
        return selectableFilters.getSorting();
    }

    private String transformName(SelectableFilters selectableFilters) {
        return selectableFilters.getName();
    }

    private String transformLastName(SelectableFilters selectableFilters) {
        return selectableFilters.getLastName();
    }

    private ArrayList<String> transformStrings(ArrayList<SelectableFilter> selectableFilters) {
        ArrayList<String> filters = new ArrayList<>();

        for (SelectableFilter selectableFilter : selectableFilters) {

            if (selectableFilter.isSelected()) {
                filters.add(selectableFilter.getName());
            }
        }
        return filters;
    }

    @Override
    public String toString() {
        return null;
    }
}
