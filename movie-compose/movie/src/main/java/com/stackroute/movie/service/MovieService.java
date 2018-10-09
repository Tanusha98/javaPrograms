package com.stackroute.movie.service;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exception.MovieAlreadyExistsException;
import com.stackroute.movie.exception.MovieNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;

//@Service
public interface MovieService {
    Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;
    Movie updateMovie(int movieId,String movieComments) throws MovieNotFoundException;
    boolean deleteMovie(Movie movie) throws MovieNotFoundException;
    List<Movie> searchMovie(String movieTitle) throws MovieNotFoundException;
    Movie searchMovieById(int movieId) throws NoSuchElementException;
    List<Movie> getAllMovies();
}
