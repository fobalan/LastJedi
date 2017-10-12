package br.com.test.lastjedi.services;

import br.com.test.lastjedi.model.Movie;
import br.com.test.lastjedi.model.MovieDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Samurai on 11/10/2017.
 */

public interface MovieDetailService {
    @Headers({
            "Accept: application/json"
    })

    @GET("3/movie/{id}")
    Call<MovieDetails> getHomePage(@Path("id") int id,
                                   @Query("api_key") String apiKey);

}
