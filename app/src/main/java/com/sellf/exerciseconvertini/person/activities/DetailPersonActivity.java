package com.sellf.exerciseconvertini.person.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.person.view.DetailPersonFragment;

public class DetailPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_person);


        //get extra numero camera and save in the variable numeroCamera
        String id = getIntent()
                .getStringExtra(getString(R.string.EXTRA_PERSON_ID));



        //start a new DetailPersonFragment instance and pass in the Person Id as argument
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = DetailPersonFragment.newInstance(id);
        fragmentManager.beginTransaction()
                .add(R.id.detail_container, fragment)
                .commit();
    }
}
