package br.com.test.lastjedi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ITST on 11/10/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResult {

    @JsonProperty("title")
    private String title;

    @JsonProperty("poster_path")
    private String posterPath;

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
