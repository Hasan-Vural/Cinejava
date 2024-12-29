package com.cinejava.singletons;

import com.cinejava.models.Movie;

public final class MovieInstanceSingleton {
  
    private Movie movie;
    private final static MovieInstanceSingleton INSTANCE = new MovieInstanceSingleton();
    
    private MovieInstanceSingleton() {}
    
    public static MovieInstanceSingleton getInstance() {
      return INSTANCE;
    }
    
    public void setMovie(Movie m) {
      this.movie = m;
    }
    
    public Movie getMovie() {
      return this.movie;
    }
  }