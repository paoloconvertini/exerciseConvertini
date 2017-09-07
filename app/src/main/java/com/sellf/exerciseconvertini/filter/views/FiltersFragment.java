package com.sellf.exerciseconvertini.filter.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.filter.listeners.IOnFilterClickedListener;
import com.sellf.exerciseconvertini.filter.models.SelectableFilters;

public class FiltersFragment extends Fragment {

    private SelectableFilters selectableFilters = new SelectableFilters();

    public FiltersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_filters, container, false);

        final EditText nameEditTxt = view.findViewById(R.id.name_filter_title);
        final EditText lastNameEditTxt = view.findViewById(R.id.cognome_filter_title);
        Button removeFiltersBtn = view.findViewById(R.id.removeFiltersBtn);
        Button showResultsBtn = view.findViewById(R.id.showResultsBtn);
        final RadioButton sortByRB = view.findViewById(R.id.sortBy_last_name_Rb); //TODO meglio una checkbox


        removeFiltersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof IOnFilterClickedListener) {
                    ((IOnFilterClickedListener) getContext()).resetFilters();
                }
            }
        });

        showResultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof IOnFilterClickedListener) {
                    if (!nameEditTxt.getText().toString().equalsIgnoreCase("")) {
                        selectableFilters.setName(nameEditTxt.getText().toString());
                    } else {
                        selectableFilters.setName(null);
                    }
                    if (!lastNameEditTxt.getText().toString().equalsIgnoreCase("")) {
                        selectableFilters.setLastName(lastNameEditTxt.getText().toString());
                    } else {
                        selectableFilters.setLastName(null);
                    }
                    if (sortByRB.isChecked()) {
                        selectableFilters.setSorting("last_name");
                    }
                    ((IOnFilterClickedListener) getContext()).onFilterClick(selectableFilters);
                }
            }
        });

        return view;
    }
}
