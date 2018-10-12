package com.stackroute.movie.service;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exception.MovieAlreadyExistsException;
import com.stackroute.movie.exception.MovieNotFoundException;
import com.stackroute.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Service
@Primary
@Qualifier("implementation1")
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie insertMovie(Movie movie) throws MovieAlreadyExistsException{
            if(!movieRepository.existsById(movie.getMovieId())) {
                Movie insertdMovie=movieRepository.insert(movie);
                if(insertdMovie!=null)
                    return insertdMovie;
                else
                    throw new MovieAlreadyExistsException("null: movie already exists");
            }
            else
                throw new MovieAlreadyExistsException("movie already exists");
    }

    @Override
    public Movie updateMovie(int movieId,String movieComments) throws MovieNotFoundException{
            if(movieRepository.existsById(movieId)){
                Movie updatedMovie=movieRepository.findById(movieId).get();
                updatedMovie.setMovieComments(movieComments);
                updatedMovie = movieRepository.insert(updatedMovie);
                return updatedMovie;
            }
            else throw new MovieNotFoundException("movie not found");
    }

    @Override
    public boolean deleteMovie(Movie movie) throws MovieNotFoundException{
            if(movieRepository.existsById(movie.getMovieId())){
                movieRepository.delete(movie);
                return true;
            }
            else throw new MovieNotFoundException("movie not found");
    }

    @Override
    public List<Movie> searchMovie(String movieTitle) throws MovieNotFoundException{
            if(movieRepository.getMovieByMovieTitle(movieTitle)!=null){
                return movieRepository.getMovieByMovieTitle(movieTitle);
            }
            else throw new MovieNotFoundException("movie not found");
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
