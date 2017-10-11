package br.com.test.lastjedi.services;

import br.com.test.lastjedi.model.Films;
import br.com.test.lastjedi.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by ITST on 11/10/2017.
 */

public interface MovieService {
    @Headers({
            "Accept: application/json"
    })

    @GET("3/search/movie")
    Call<Movie> getMovie(@Query("api_key") String apiKey,
                         @Query("query") String query);
}
