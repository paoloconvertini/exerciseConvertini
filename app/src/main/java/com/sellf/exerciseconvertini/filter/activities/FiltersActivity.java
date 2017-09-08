package com.sellf.exerciseconvertini.filter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.filter.listeners.IOnFilterClickedListener;
import com.sellf.exerciseconvertini.filter.models.SelectableFilters;
import com.sellf.exerciseconvertini.filter.views.FiltersFragment;

/**
 * Created by pconvertini on 20/07/2017.
 */

public class FiltersActivity extends AppCompatActivity implements IOnFilterClickedListener {
    public final static String SELECTED_FILTER_EXTRA = "SELECTED_FILTER_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        /**
         * start a new {@link FiltersFragment}
         */
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new FiltersFragment();
        fragmentManager.beginTransaction()
                .add(R.id.activity_filter_container, fragment)
                .commit();
    }

    @Override
    public void onFilterClick(SelectableFilters selectableFilters) {
        Intent intent = new Intent();
        intent.putExtra(SELECTED_FILTER_EXTRA, selectableFilters);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void resetFilters() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
