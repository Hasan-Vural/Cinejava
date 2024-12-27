package com.cinejava.services;

import com.cinejava.constants.DataStoreConstants;
import com.cinejava.models.Movie;

public class MoviesService extends GenericService<Movie> {
    public MoviesService() {
        super(Movie.class, DataStoreConstants.MOVIE_STORE_NAME);
    }
}
