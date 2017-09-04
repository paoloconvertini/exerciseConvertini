package com.sellf.exerciseconvertini.person.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sellf.exerciseconvertini.API.Api;
import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.person.model.Person;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPersonFragment extends Fragment {

    private String id;
    private Api api;
    private View loadingIndicator;
    private TextView fullname;
    private TextView title;
    private TextView skype;
    private TextView email;
    private TextView address;

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
        loadingIndicator = personView.findViewById(R.id.loading_indicator_fragment_person_detail);
        fullname = personView.findViewById(R.id.full_name);
        title = personView.findViewById(R.id.title);
        skype = personView.findViewById(R.id.skype);
        email = personView.findViewById(R.id.email);
        address = personView.findViewById(R.id.address);
        getPerson(id);
        return personView;
    }

    // Hide loading indicator because the data has been loaded
    private void hideLoadingIndicator() {
        // Hide loading indicator because the data has been loaded
        if (getView() != null) {
            View loadingIndicator = getView()
                    .findViewById(R.id.loading_indicator_fragment_person_detail);
            if (loadingIndicator.getVisibility() == View.VISIBLE)
                loadingIndicator.setVisibility(View.GONE);
        }
    }

    private void getPerson(String id) {
        api = new Api();
        Call<Person> result = api.getPerson(id);
        result.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                updateUI(response.body());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                hideLoadingIndicator();
            }
        });
    }

    private void updateUI(Person person) {
        if (getView() != null) {
            hideLoadingIndicator();
            fullname.setText(person.getFullName());
            title.setText(person.getTitle());
            skype.setText(person.getSkype());
            email.setText(person.getEmail());
            address.setText(person.getAddress());
        }
    }

}
