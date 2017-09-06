package com.sellf.exerciseconvertini.API;

import com.sellf.exerciseconvertini.person.models.People;
import com.sellf.exerciseconvertini.person.models.Person;
import com.sellf.exerciseconvertini.user.models.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pconvertini on 30/06/2017.
 */


public interface ApiService {

    @GET("people")
    Call<People> getPeopleList();

    @GET("users")
    Call<Users> getUserList();

   @GET("people")
    Call<People> getPeopleListFiltered(@Query("first_name") String firstName,
                                       @Query("last_name") String lastName,
                                       @Query("sort_by") String sortBy);

    @GET("people/{id}")
    Call<Person>getPerson(@Path("id") String id);

    @POST("people")
    Call<Person>createPerson(@Body Person person);
}
