package br.com.test.lastjedi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Samurai on 06/10/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class People implements Serializable {

        private int id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("height")
        private int height;

        @JsonProperty("mass")
        private int mass;

        @JsonProperty("hair_color")
        private String hairColor;

        @JsonProperty("skin_color")
        private String skinColor;

        @JsonProperty("eye_color")
        private String eyeColor;

        @JsonProperty("birth_year")
        private String birthYear;

        @JsonProperty("gender")
        private String gender;

        @JsonProperty("homeworld")
        private String homeworld;

        @JsonProperty("films")
        private List <String> films;

        @JsonProperty("species")
        private List <String> species;

        @JsonProperty("vehicles")
        private List <String> vehicles;

        @JsonProperty("starships")
        private List <String> starships;

        @JsonProperty("created")
        private String created;

        @JsonProperty("edited")
        private String edited;

        @JsonProperty("url")
        private String url;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getMass() {
        return mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getGender() {
        return gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public List<String> getFilms() {
        return films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public List<String> getStarships() {
        return starships;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
