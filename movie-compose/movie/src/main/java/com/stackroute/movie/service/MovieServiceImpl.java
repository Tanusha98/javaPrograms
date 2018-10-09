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
//    private static final Logger LOG
//            = Logger.getLogger(String.valueOf(MovieServiceImpl.class));
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//    //    LOG.info("Adding default values");
//        Movie movie=new Movie(1,"Devdas1","Telugu1","NaniAL", LocalDate.of(2018,9,10));
//        movieRepository.save(movie);
//        movie=new Movie(2,"Devdas2","Telugu2","NagarjunaAL", LocalDate.of(2018,9,10));
//        movieRepository.save(movie);
//    }
////    @EventListener
////    public void onApplicationEvent(ContextRefreshedEvent event) {
////        LOG.info("Adding default values");
////        Movie movie=new Movie(1,"Devdas1","Telugu1","NaniEL", LocalDate.of(2018,9,10));
////        movieRepository.save(movie);
////        movie=new Movie(2,"Devdas2","Telugu2","NagarjunaEL", LocalDate.of(2018,9,10));
////        movieRepository.save(movie);
////    }
//
//    @Override
//    public void run(String...args) throws Exception {
//    //    LOG.info("Increment counter");
//        Movie movie=new Movie(3,"Devdas3","Telugu3","NaniCLR", LocalDate.of(2018,9,10));
//        movieRepository.save(movie);
//        movie=new Movie(4,"Devdas4","Telugu4","NagarjunaCLR", LocalDate.of(2018,9,10));
//        movieRepository.save(movie);
//    }

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException{
            if(!movieRepository.existsById(movie.getMovieId())) {
                Movie savedMovie=movieRepository.save(movie);
                return savedMovie;
            }
            else
                throw new MovieAlreadyExistsException("movie already exists");
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
