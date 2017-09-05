package com.sellf.exerciseconvertini.person.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sellf.exerciseconvertini.API.Api;
import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.person.activities.PeopleListActivity;
import com.sellf.exerciseconvertini.person.model.Person;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreatePersonFragment extends Fragment {

    private String userId;
    private EditText firstname;
    private EditText lastname;
    private EditText title;
    private EditText company;
    private EditText email;
    private EditText address;
    private EditText phone;
    private Button saveBtn;
    private Api api;

    public CreatePersonFragment() {
        // Required empty public constructor
    }

    /**
     * Create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userId is the user Id.
     * @return A new instance of fragment CreatePersonFragment.
     */
    public static CreatePersonFragment newInstance(String userId) {
        CreatePersonFragment fragment = new CreatePersonFragment();
        Bundle args = new Bundle();
        args.putString(String.valueOf(R.string.EXTRA_USER_ID), userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString(String.valueOf(R.string.EXTRA_USER_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_person, container, false);
        firstname = view.findViewById(R.id.first_name);
        lastname = view.findViewById(R.id.last_name);
        title = view.findViewById(R.id.title_person_create);
        company = view.findViewById(R.id.company);
        email = view.findViewById(R.id.email);
        address = view.findViewById(R.id.address);
        phone = view.findViewById(R.id.phone);
        saveBtn = view.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPerson(new Person(company.getText().toString(), firstname.getText().toString(), userId,
                        title.getText().toString(), phone.getText().toString(), lastname.getText().toString(),
                        address.getText().toString(), email.getText().toString()));
            }
        });
        return view;
    }

    private void createPerson(Person person) {
        api = new Api();
        Call<Person> postPerson = api.createPerson(person);
        postPerson.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                response.body();
                Toast.makeText(getContext(), "Contatto aggiunto con successo", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), PeopleListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                try {
                    if (getView() != null) {
                        TextView textView = getView()
                                .findViewById(R.id.emptyViewTxt);
                        textView.setText(R.string.empty_list_text);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
