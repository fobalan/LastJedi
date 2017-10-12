package br.com.test.lastjedi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Samurai on 11/10/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetails {

    @JsonProperty("homepage")
    private String homePage;

    public String getHomePage() {
        return homePage;
    }
}
