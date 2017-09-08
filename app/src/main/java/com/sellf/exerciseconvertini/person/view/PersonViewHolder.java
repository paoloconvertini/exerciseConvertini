package com.sellf.exerciseconvertini.person.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.person.models.Person;

/**
 * Created by pconvertini on 04/09/2017.
 */

public class PersonViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout layout;
    private TextView personNameTextView;
    private TextView personTitleTextView;
    private Person person;
    private TextView initialName;


    public static PersonViewHolder newInstance(ViewGroup parent) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_row_view, parent, false);

        return new PersonViewHolder(v);
    }

    public PersonViewHolder(LinearLayout itemView) {
        super(itemView);
        layout = itemView;
        this.personTitleTextView = layout.findViewById(R.id.person_title);
        this.personNameTextView = layout.findViewById(R.id.person_name);
        this.initialName = layout.findViewById(R.id.initNameTxtView);
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        this.setupView();
    }

    private void setupView() {
        //set the ListView with the contact list data if there is an array of people.
        if (person != null) {
            personNameTextView.setText(person.getFullName());
            personTitleTextView.setText(person.getTitle());
            String initName = person.getFirstName().substring(0,1);
            String initLastName = person.getLastName().substring(0,1);
            initialName.setText(String.format("%s%s", initName, initLastName));
        }


    }
}
