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
        Fragment fragment = CreatePersonFragment.newInstance("113310"); //non ho trovato endpoint per postare i miei dati di login, ma ho
        //notato che comunque c'è bisogno di specificare un user id per creare un nuovo contatto
        fragmentManager.beginTransaction()
                .add(R.id.create_person_container, fragment)
                .commit();
    }
}
