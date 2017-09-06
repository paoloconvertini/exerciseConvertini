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

import com.google.gson.Gson;
import com.sellf.exerciseconvertini.API.Api;
import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.error.models.ErrorBody;
import com.sellf.exerciseconvertini.person.activities.PeopleListActivity;
import com.sellf.exerciseconvertini.person.models.Person;
import com.sellf.exerciseconvertini.user.models.User;
import com.sellf.exerciseconvertini.user.models.Users;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreatePersonFragment extends Fragment {

    private int userId;
    private EditText firstName;
    private EditText lastName;
    private EditText title;
    private EditText company;
    private EditText email;
    private EditText address;
    private EditText phone;
    private ArrayList<User> userList = new ArrayList<>();

    public CreatePersonFragment() {
        // Required empty public constructor
    }

    /**
     * Create a new instance of
     * this fragment using the provided parameters.
     *
     * param  is the user Id.
     * return A new instance of fragment CreatePersonFragment.
     */
   /* public static CreatePersonFragment newInstance(int userId) {
        CreatePersonFragment fragment = new CreatePersonFragment();
        Bundle args = new Bundle();
        args.putInt(String.valueOf(R.string.EXTRA_USER_ID), userId);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  if (getArguments() != null) {
            userId = getArguments().getInt(String.valueOf(R.string.EXTRA_USER_ID));
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_person, container, false);
        firstName = view.findViewById(R.id.first_name);
        lastName = view.findViewById(R.id.last_name);
        title = view.findViewById(R.id.title_person_create);
        company = view.findViewById(R.id.company);
        email = view.findViewById(R.id.email);
        address = view.findViewById(R.id.address);
        phone = view.findViewById(R.id.phone);

        new Api().getUserList().enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.code() == 200 && response.body() != null) {
                    userList.addAll(response.body().getUserList());
                    userId = userList.get(0).getId();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                t.printStackTrace();
            }
        });


        Button saveBtn = view.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPerson(new Person(company.getText().toString(), firstName.getText().toString(), userId,
                        title.getText().toString(), phone.getText().toString(), lastName.getText().toString(),
                        address.getText().toString(), email.getText().toString()));
            }
        });
        return view;
    }

    private void createPerson(Person person) {

        //mandatory fields check
        if (person.getFirstName().equals("") || person.getLastName().equals("") ||
                person.getEmail().equals("") || person.getPhone().equals("")) {

            Toast.makeText(getContext(), "Nome, Cognome, Email e telefono" +
                    " non possono essere vuoti", Toast.LENGTH_LONG).show();

        } else {

            new Api().createPerson(person).enqueue(new Callback<Person>() {

                //Handle the response to createPerson method
                @Override
                public void onResponse(Call<Person> call, Response<Person> response) {

                    switch (response.code()) {
                        case 422:
                            try {
                                ErrorBody errorBody = new Gson().fromJson(response.errorBody().string(),
                                        ErrorBody.class);
                                Toast.makeText(getContext(), errorBody.getError().getDetails().getEmail() +
                                        " for email field",
                                        Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 201:
                            Toast.makeText(getContext(), "Contatto aggiunto con successo", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), PeopleListActivity.class);
                            startActivity(intent);
                            break;
                    }

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
}
