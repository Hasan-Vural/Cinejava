package com.cinejava.services.interfaces;

import java.util.List;

import com.cinejava.models.Movie;

public interface IMoviesService {
    List<Movie> getBySliderState(int index);
    int getCurrentRevenue(long id);
}
