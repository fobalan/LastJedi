package br.com.test.lastjedi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by ITST on 11/10/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @JsonProperty("results")
    private List<MovieResult> movieResult;

    public List<MovieResult> getMovieResult() {
        return movieResult;
    }
}
