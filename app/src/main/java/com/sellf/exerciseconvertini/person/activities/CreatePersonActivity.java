package com.sellf.exerciseconvertini.person.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.person.view.CreatePersonFragment;

public class CreatePersonActivity extends AppCompatActivity {

    private CreatePersonFragment fragment = new CreatePersonFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_person);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button saveBtn = toolbar.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                * Call the method from CreatePersonFRagment to save a new contact
                * */
                fragment.onSavePerson();
            }
        });
        //start a new DetailPersonFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.create_person_container, fragment)
                .commit();
    }
}
