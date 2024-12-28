package com.cinejava.services.interfaces;

import java.util.List;

import com.cinejava.models.Movie;

public interface IMoviesService extends IGenericService<Movie> {
    List<Movie> getBySliderState(int index);
    int getCurrentRevenue(long id);
}
