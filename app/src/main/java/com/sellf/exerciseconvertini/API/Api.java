package com.sellf.exerciseconvertini.API;

import com.google.gson.GsonBuilder;
import com.sellf.exerciseconvertini.person.model.Person;
import com.sellf.exerciseconvertini.utils.DateTypeAdapter;
import com.sellf.exerciseconvertini.App;
import com.sellf.exerciseconvertini.R;
import com.sellf.exerciseconvertini.person.model.People;

import org.joda.time.LocalDateTime;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

/**
 * Created by pconvertini on 30/06/2017.
 */

public class Api implements ApiService {

    private ApiService apiService;

    public Api() {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request.Builder newRequest = request
                                .newBuilder()
                                .header("Api-Key", App.getContext().getString(R.string.Api_KEY))
                                ;

                        return chain.proceed(newRequest.build());
                    }
                });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(App.getContext().getString(R.string.SERVER_URL))
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class, new DateTypeAdapter())
                        .create()))
                .client(client.build())
                .build();
        this.apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Call<People> getPeopleList() {
        return this.apiService.getPeopleList();
    }

    @Override
    public Call<Person> getPerson(@Path("id") String id) {
        return this.apiService.getPerson(id);
    }
}

