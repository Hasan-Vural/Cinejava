package com.cinejava.services.implementations;

import java.security.InvalidParameterException;
import java.util.List;
import com.cinejava.constants.DataStoreConstants;
import com.cinejava.models.Movie;
import com.cinejava.services.interfaces.IMoviesService;

public class MoviesService extends GenericService<Movie> implements IMoviesService {

    private final ReservationsService reservationsService;

    public MoviesService() {
        super(Movie.class, DataStoreConstants.MOVIE_STORE_NAME);
        reservationsService = new ReservationsService();
    }
    
    @Override
    public List<Movie> getBySliderState(int index) throws InvalidParameterException{

        if (index < 0) {
            throw new InvalidParameterException();
        }

        List<Movie> items = dataStoreContext.getAll();

        if (index+2 >= items.size()) {
            throw new InvalidParameterException();
        }

        return items.subList(index, index+2);
    }

    @Override
    public int getCurrentRevenue(long id) throws InvalidParameterException{
        if (id < 0) {
            throw new InvalidParameterException("Invalid movie ID: " + id);
        }

        Movie movie = dataStoreContext.get(id)
            .orElseThrow(() -> new InvalidParameterException("Movie not found with ID: " + id));

        int totalRevenue = reservationsService.getAll()
            .stream()
            .filter(reservation -> reservation.getMovieId() == id)
            .mapToInt(reservation -> reservation.getReservedSeats().size() * movie.getTicketPrice())
            .sum();
    
        return totalRevenue;
    }
}
