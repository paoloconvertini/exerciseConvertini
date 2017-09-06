package com.sellf.exerciseconvertini.person.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sellf.exerciseconvertini.API.Api;
import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.filter.activities.FiltersActivity;
import com.sellf.exerciseconvertini.filter.models.Filters;
import com.sellf.exerciseconvertini.filter.models.SelectableFilters;
import com.sellf.exerciseconvertini.filter.transformers.SelectableFiltersToFiltersTransformer;
import com.sellf.exerciseconvertini.person.activities.CreatePersonActivity;
import com.sellf.exerciseconvertini.person.adapters.PersonRecyclerViewAdapter;
import com.sellf.exerciseconvertini.person.listeners.IOnStartNewActivityListener;
import com.sellf.exerciseconvertini.person.models.People;
import com.sellf.exerciseconvertini.person.models.Person;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class PeopleListFragment extends Fragment implements Callback<People> {

    private final static String EXTRA = "EXTRA";
    private final static int FILTER_REQUEST_CODE = 3000;
    private ArrayList<Person> peopleList = new ArrayList<>();
    private IOnStartNewActivityListener listener;
    private RecyclerView mRecyclerView;
    private TextView ErrorTxtView;
    private Filters filters = new Filters();
    private RecyclerView.Adapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PeopleListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (IOnStartNewActivityListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people_list, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatePersonActivity.class);
                startActivity(intent);

            }
        });

        if (getArguments() != null) {
            SelectableFilters selectableFilters = (SelectableFilters) getArguments().getSerializable(EXTRA);
            //dall'intent ricavo i filtri selezionati
            this.filters = new SelectableFiltersToFiltersTransformer(selectableFilters).transform();
            new Api().getPeopleListFiltered(this.filters.getName(),
                    this.filters.getLastName(), this.filters.getSorting()).enqueue(new Callback<People>() {
                @Override
                public void onResponse(Call<People> call, Response<People> response) {
                    hideLoadingIndicator();
                    if (response.body() != null) {
                        if (peopleList != null) {
                            peopleList.clear();
                            peopleList.addAll(response.body().getPeopleList());
                        }
                        adapter = new PersonRecyclerViewAdapter(listener, peopleList, mRecyclerView);
                        mRecyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<People> call, Throwable t) {
                    hideLoadingIndicator();
                    if (getView() != null) {
                        ErrorTxtView = getView()
                                .findViewById(R.id.emptyViewTxt);
                        ErrorTxtView.setText(R.string.empty_list_text);
                    }
                }
            });
        } else {
            sendRequestPeopleList();
        }
        // Set the adapter
        mRecyclerView = view.findViewById(R.id.recyclerViewPeopleList);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        return view;
    }


    //Send the request to the web server to retrieve the list of rooms
    private void sendRequestPeopleList() {
        //call the server, get the Json data and convert into a Java object

        Call<People> requestPeopleList = new Api().getPeopleList();
        requestPeopleList.enqueue(this);
    }

    @Override
    public void onResponse(Call<People> call, Response<People> response) {
        switch (response.code()) {
            case 401:
                hideLoadingIndicator();
                Toast.makeText(getContext(), getString(R.string.AUTORIZZAZIONE_NEGATA), Toast.LENGTH_LONG)
                        .show();
                break;
            case 200:
                hideLoadingIndicator();
                if (response.body() != null) {
                    if (peopleList != null) {
                        peopleList.clear();
                        peopleList.addAll(response.body().getPeopleList());
                    }
                    adapter = new PersonRecyclerViewAdapter(listener, peopleList, mRecyclerView);
                    mRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                }
        }
    }

    @Override
    public void onFailure(Call<People> call, Throwable t) {
        hideLoadingIndicator();
        if (getView() != null) {
            ErrorTxtView = getView()
                    .findViewById(R.id.emptyViewTxt);
            ErrorTxtView.setText(R.string.empty_list_text);
        }
    }

    // Hide loading indicator because the data has been loaded

    public void hideLoadingIndicator() {
        // Hide loading indicator because the data has been loaded
        if (getView() != null) {
            View loadingIndicator = getView()
                    .findViewById(R.id.loading_indicator_fragment_people_list);
            if (loadingIndicator.getVisibility() == View.VISIBLE)
                loadingIndicator.setVisibility(View.GONE);
        }
    }

    public static PeopleListFragment newInstance(SelectableFilters selectableFilters) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA, selectableFilters);
        PeopleListFragment fragment = new PeopleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILTER_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                SelectableFilters selectableFilters = (SelectableFilters)
                        data.getSerializableExtra(FiltersActivity.SELECTED_FILTER_EXTRA);
                this.filters = new SelectableFiltersToFiltersTransformer(selectableFilters).transform();
                new Api().getPeopleListFiltered(this.filters.getName(),
                        this.filters.getLastName(), this.filters.getSorting()).enqueue(new Callback<People>() {
                    @Override
                    public void onResponse(Call<People> call, Response<People> response) {
                        hideLoadingIndicator();
                        if (response.body() != null) {
                            if (peopleList != null) {
                                peopleList.clear();
                                peopleList.addAll(response.body().getPeopleList());
                            }
                            adapter = new PersonRecyclerViewAdapter(listener, peopleList, mRecyclerView);
                            mRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<People> call, Throwable t) {
                        hideLoadingIndicator();
                        if (getView() != null) {
                            ErrorTxtView = getView()
                                    .findViewById(R.id.emptyViewTxt);
                            ErrorTxtView.setText(R.string.empty_list_text);
                        }
                    }
                });
            }
        }

        if (resultCode == RESULT_CANCELED) {
            sendRequestPeopleList();
        }

    }
}