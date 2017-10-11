package br.com.test.lastjedi.services;

import br.com.test.lastjedi.model.Films;
import br.com.test.lastjedi.model.People;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Samurai on 06/10/2017.
 */

public interface FilmsService {
    @Headers({
            "Accept: application/json"
    })

    @GET(".")
    Call<Films> getFilms();
}
