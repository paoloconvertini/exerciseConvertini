package com.sellf.exerciseconvertini.API;

import com.sellf.exerciseconvertini.person.model.People;
import com.sellf.exerciseconvertini.person.model.Person;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by pconvertini on 30/06/2017.
 */


public interface ApiService {

    @GET("people")
    Call<People> getPeopleList();

    @GET("people/{id}")
    Call<Person>getPerson(@Path("id") String id);



 /*   @POST("/dhp-ws/public/api/hotel/roomstatuslist")
    Call<ArrayList<Room>> getRoomStatusList(@Body Filters filters);

    @POST("/dhp-ws/public/api/hotel/minibarlist")
    Call<ArrayList<Room>> getRoomMinibarList(@Body Filters filters);

    @GET("/dhp-ws/public/api/hotel/checkinlist")
    Call<ArrayList<Room>> getRoomCheckInList();

    @GET("/dhp-ws/public/api/hotel/filterlist")
    Call<Filters> getFilterList();

    @POST("/dhp-ws/public/api/hotel/roomstatusupdate")
    Call<Room> postStatusRoom(@Body ResponseRoom room);

    @POST("/api/login-service")
    Call<UserConfigData> getConfigData(@Body User user);

    @POST("/dhp-ws/public/api/hotel/roomcharge")
    Call<List<CodMat>> sendAddebitoMinibar(@Body ResponseAddebitoMinibar responseAddebitoMinibar);

    @POST("/dhp-ws/public/api/hotel/historychargelist")
    Call<ArrayList<HistoryCharge>> getHistoryCharge(@Body SendHistoryCharge sendHistoryCharge);*/
}
