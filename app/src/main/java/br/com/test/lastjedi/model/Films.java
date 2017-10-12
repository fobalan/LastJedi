package br.com.test.lastjedi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ITST on 11/10/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Films {

    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("episode_id")
    private int episodeId;

    @JsonProperty("opening_crawl")
    private String openingCrawl;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("url")
    private String url;

    private String imageUrl;

    private String homePage;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHomePage(){
        return homePage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public void setHomePage(String homePage) { this.homePage = homePage; }

}
