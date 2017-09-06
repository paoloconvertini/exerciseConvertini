package com.sellf.exerciseconvertini.person.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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

        /**
         * start a new {@link PeopleListFragment} instance and pass
         * in the room number as arguments
         */
        FragmentManager fragmentManager = getSupportFragmentManager();
        peopleFragment = new PeopleListFragment();
        fragmentManager.beginTransaction()
                .add(R.id.list_people_container, peopleFragment)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.filters) {
            peopleFragment.startActivityForResult(new Intent(this, FiltersActivity.class), FILTER_REQUEST_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startNewActivity(Person person) {
        if (findViewById(R.id.detail_container) == null) {
            Intent intent = new Intent(this, DetailPersonActivity.class);
            intent.putExtra(getString(R.string.EXTRA_PERSON_ID), person.getId());
            startActivityForResult(intent, 0);
        }
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(requestCode == FILTER_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                SelectableFilters selectableFilters = (SelectableFilters)
                        data.getSerializableExtra(FiltersActivity.SELECTED_FILTER_EXTRA);
                Fragment fragment = PeopleListFragment.newInstance(selectableFilters);
                fragmentManager.beginTransaction()
                        .add(R.id.list_people_container, fragment)
                        .commit();

            }
        }

        if(resultCode == RESULT_CANCELED) {

            Fragment fragment = new PeopleListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.list_people_container, fragment)
                    .commit();
        }

    }*/
}
