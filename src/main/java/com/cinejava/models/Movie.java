package com.cinejava.models;

import java.util.List;

import com.cinejava.common.BaseModel;
import com.cinejava.constants.DataStoreConstants;

public class Movie extends BaseModel {
    public String name;
    public String synopsis;
    public List<String> genres;
    public int duration;
    public double imdbRating;
    public String image;


    public Movie(String name, String synopsis, List<String> genres, int duration, double imdbRating, String image) {
        super();
        this.name = name;
        this.synopsis = synopsis;
        this.genres = genres;
        this.duration = duration;
        this.imdbRating = imdbRating;
        this.image = image;
    }

    public Movie() {
        super();
    }

    public static String getStoreName(){
        return DataStoreConstants.MOVIE_STORE_NAME;
    }
}
