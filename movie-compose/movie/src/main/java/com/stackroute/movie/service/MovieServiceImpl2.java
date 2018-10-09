package com.stackroute.movie.service;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exception.MovieAlreadyExistsException;
import com.stackroute.movie.exception.MovieNotFoundException;
import com.stackroute.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("implementation2")
public class MovieServiceImpl2 implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException{
        try{
            if(!movieRepository.existsById(movie.getMovieId())) {
                Movie savedMovie=movieRepository.save(movie);
                return savedMovie;
            }
            else
                throw new MovieAlreadyExistsException("movie already exists");
        }
        catch (MovieAlreadyExistsException e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public Movie updateMovie(int movieId,String movieComments) throws MovieNotFoundException{
        if(movieRepository.existsById(movieId)){
            Movie updatedMovie=movieRepository.findById(movieId).get();
            updatedMovie.setMovieComments(movieComments);
            updatedMovie = movieRepository.save(updatedMovie);
            return updatedMovie;
        }
        else throw new MovieNotFoundException("movie not found");
    }

    @Override
    public boolean deleteMovie(Movie movie) {
        try {
            if(movieRepository.existsById(movie.getMovieId())){
                movieRepository.delete(movie);
                return true;
            }
            else throw new MovieNotFoundException("movie not found");
        }
        catch (MovieNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Movie> searchMovie(String movieTitle) {
        try {
            if(movieRepository.getMovieByMovieTitle(movieTitle)!=null){
                return movieRepository.getMovieByMovieTitle(movieTitle);
            }
            else throw new MovieNotFoundException("movie not found");
        }
        catch (MovieNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Movie searchMovieById(int movieId){
        return movieRepository.findById(movieId).get();
    }

    @Override
    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepository.findAll();
    }

}
