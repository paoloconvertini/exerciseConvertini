package com.sellf.exerciseconvertini.person.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sellf.exerciseconvertini.person.listeners.IOnStartNewActivityListener;
import com.sellf.exerciseconvertini.person.model.Person;
import com.sellf.exerciseconvertini.person.view.PersonViewHolder;

import java.util.ArrayList;

public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    private ArrayList<Person> peopleList;
    private final IOnStartNewActivityListener mListener;
    private RecyclerView recyclerView;

    public PersonRecyclerViewAdapter(IOnStartNewActivityListener listener,
                                     ArrayList<Person> peopleList, RecyclerView recyclerView) {
        this.mListener = listener;
        this.peopleList = peopleList;
        this.recyclerView = recyclerView;
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
            Person person = peopleList.get(itemPosition);

            if (mListener != null && person != null) {
                mListener.startNewActivity(person);
            }
        }
    };

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PersonViewHolder holder = PersonViewHolder.newInstance(parent);
        holder.getLayout().setOnClickListener(mOnClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.setPerson(peopleList.get(position));
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

}
