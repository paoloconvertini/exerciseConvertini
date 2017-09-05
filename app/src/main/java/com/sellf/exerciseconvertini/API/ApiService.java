package com.sellf.exerciseconvertini.API;

import com.sellf.exerciseconvertini.person.models.People;
import com.sellf.exerciseconvertini.person.models.Person;

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

    @GET("people")
    Call<People> getPeopleListFilteredByName(@Query("first_name") String firstName);

    @GET("people")
    Call<People> getPeopleListFilteredByLastName(@Query("last_name") String lastName);

    @GET("people")
    Call<People> getPeopleListSortByLastName(@Query("last_name") String lastName);

    @GET("people/{id}")
    Call<Person>getPerson(@Path("id") String id);

    @POST("people")
    Call<Person>createPerson(@Body Person person);

 /*

    @POST("/dhp-ws/public/api/hotel/minibarlist")
    Call<ArrayList<Room>> getRoomMinibarList(@Body Filters filters);
*/
}
