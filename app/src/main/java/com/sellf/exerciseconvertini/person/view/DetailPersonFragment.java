package com.sellf.exerciseconvertini.person.view;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.sellf.exerciseconvertini.API.Api;
import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.person.models.Person;
import com.sellf.exerciseconvertini.utils.MyFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPersonFragment extends MyFragment {

    private String id;
    private TextView fullName;
    private TextView initFullName;
    private EditText phone;
    private EditText title;
    private EditText skype;
    private EditText email;
    private EditText address;
    private EditText ErrorTxtView;
    private ConstraintLayout fullNameLayout;
    private ConstraintLayout contactDetailsLayout;

    public DetailPersonFragment() {
        // Required empty public constructor
    }

    /**
     * Create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id is the Person Id.
     * @return A new instance of fragment DetailPersonFragment.
     */
    public static DetailPersonFragment newInstance(String id) {
        DetailPersonFragment fragment = new DetailPersonFragment();
        Bundle args = new Bundle();
        args.putString(String.valueOf(R.string.EXTRA_PERSON_ID), id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(String.valueOf(R.string.EXTRA_PERSON_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View personView = inflater.inflate(R.layout.fragment_detail_person, container, false);
        fullNameLayout = personView.findViewById(R.id.full_name_layout);
        contactDetailsLayout = personView.findViewById(R.id.contact_details_layout);
        fullName = fullNameLayout.findViewById(R.id.full_name);
        initFullName = fullNameLayout.findViewById(R.id.init_full_name);
        phone = contactDetailsLayout.findViewById(R.id.phone);
        phone.setEnabled(false);
        title = personView.findViewById(R.id.title_person_detail);
        title.setEnabled(false);
        skype = personView.findViewById(R.id.skype);
        skype.setEnabled(false);
        email = personView.findViewById(R.id.email);
        email.setEnabled(false);
        address = personView.findViewById(R.id.address);
        address.setEnabled(false);
        getPerson(id);
        return personView;
    }

    private void getPerson(String id) {
        new Api().getPerson(id).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                updateUI(response.body());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

                hideLoadingIndicator(getView(),
                        R.id.loading_indicator_fragment_person_detail);

                errorText(getView(), R.id.emptyViewTxt, R.string.empty_list_text);
            }
        });
    }

    private void updateUI(Person person) {
        if (getView() != null) {
            hideLoadingIndicator(getView(),
                    R.id.loading_indicator_fragment_person_detail);
            initFullName.setText(String.format("%s%s", person.getFirstName().substring(0,1),
                    person.getLastName().substring(0,1)));
            phone.setText(person.getPhone());
            fullName.setText(person.getFullName());
            title.setText(person.getTitle());
            skype.setText(person.getSkype());
            email.setText(person.getEmail());
            address.setText(person.getAddress());
        }
    }

}
