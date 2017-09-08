package com.sellf.exerciseconvertini.person.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.filter.activities.FiltersActivity;
import com.sellf.exerciseconvertini.person.listeners.IOnStartNewActivityListener;
import com.sellf.exerciseconvertini.person.models.Person;
import com.sellf.exerciseconvertini.person.view.PeopleListFragment;

public class PeopleListActivity extends AppCompatActivity implements IOnStartNewActivityListener {


    Fragment peopleFragment;
    private final static int FILTER_REQUEST_CODE = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_people);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton filterBtn = toolbar.findViewById(R.id.filterBtn);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peopleFragment.startActivityForResult(new Intent(
                        PeopleListActivity.this, FiltersActivity.class), FILTER_REQUEST_CODE);
            }
        });
        /**
         * start a new {@link PeopleListFragment}
         */
        FragmentManager fragmentManager = getSupportFragmentManager();
        peopleFragment = new PeopleListFragment();
        fragmentManager.beginTransaction()
                .add(R.id.list_people_container, peopleFragment)
                .commit();
    }

    /** Method needs to be implemented from {@link com.sellf.exerciseconvertini.person.listeners.IOnStartNewActivityListener}
    *
     */
    @Override
    public void startNewActivity(Person person) {
        if (findViewById(R.id.detail_container) == null) {
            Intent intent = new Intent(this, DetailPersonActivity.class);
            intent.putExtra(getString(R.string.EXTRA_PERSON_ID), person.getId());
            startActivityForResult(intent, 0);
        }
    }
}
