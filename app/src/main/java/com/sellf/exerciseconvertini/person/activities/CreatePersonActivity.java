package com.sellf.exerciseconvertini.person.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.person.view.CreatePersonFragment;

public class CreatePersonActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_person);




        //start a new DetailPersonFragment instance and pass in the Person Id as argument
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new CreatePersonFragment();
        fragmentManager.beginTransaction()
                .add(R.id.create_person_container, fragment)
                .commit();
    }
}
