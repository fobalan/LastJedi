package br.com.test.lastjedi.services;

import java.util.List;

import br.com.test.lastjedi.model.People;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Samurai on 06/10/2017.
 */

public interface PeopleService {
    @Headers({
            "Accept: application/json"
    })

    @GET(".")
    Call<People> getPeople();
}
