package br.com.test.lastjedi.retrofit;

import br.com.test.lastjedi.services.PeopleService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Samurai on 06/10/2017.
 */

public class RetrofitInitializer {
    private final Retrofit retrofit;

    public RetrofitInitializer(String url){

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public PeopleService getPeopleService() {
        return retrofit.create(PeopleService.class);
    }

}
