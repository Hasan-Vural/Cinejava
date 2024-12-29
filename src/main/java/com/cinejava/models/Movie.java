package com.cinejava.models;

import java.util.List;

import com.cinejava.common.BaseModel;
import com.cinejava.constants.DataStoreConstants;

// İnternetten Jackson kütüphanesi ile kontrol edildi düzgün formata geitirldi jSon için 

public class Movie extends BaseModel {
    private String name;
    private String synopsis;
    private List<String> genres;
    private int duration;
    private double imdbRating;
    private int ticketPrice;
    private String image;


    public Movie(String name, String synopsis, List<String> genres, int duration, double imdbRating, int ticketPrice, String image) {
        this.name = name;
        this.synopsis = synopsis;
        this.genres = genres;
        this.duration = duration;
        this.imdbRating = imdbRating;
        this.image = image;
    }

    public Movie() {
    }

    public static String getStoreName(){
        return DataStoreConstants.MOVIE_STORE_NAME;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
}
