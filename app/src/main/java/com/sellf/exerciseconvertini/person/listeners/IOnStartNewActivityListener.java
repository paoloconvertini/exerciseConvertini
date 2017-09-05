package com.sellf.exerciseconvertini.person.listeners;

import com.sellf.exerciseconvertini.person.models.Person;

/**
 * Created by pconvertini on 04/09/2017.
 */

public interface IOnStartNewActivityListener {
    /**
     * Start a new
     * {@link com.sellf.exerciseconvertini.person.activities.DetailPersonActivity}
     *
     * @param Person is the person obj
     * */
    void startNewActivity(Person person);
}
