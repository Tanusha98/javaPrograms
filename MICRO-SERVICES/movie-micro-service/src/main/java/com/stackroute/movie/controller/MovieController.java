package com.stackroute.movie.controller;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exception.MovieAlreadyExistsException;
import com.stackroute.movie.exception.MovieNotFoundException;
import com.stackroute.movie.service.MovieService;
//import com.stackroute.movie.service.MovieServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/v1")
public class MovieController {

    private MovieService movieService;
//
//    public MovieService getMovieService() {
//        return movieService;
//    }
//
//   // @Autowired
//    public void setMovieService(MovieService movieService) {
//        this.movieService = movieService;
//    }

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(value = "movie")
    public ResponseEntity<?> insertMovie(@RequestBody Movie movie){
        ResponseEntity responseEntity;
        try{
            Movie insertdMovie = movieService.insertMovie(movie);
            responseEntity=new ResponseEntity<Movie>(insertdMovie, HttpStatus.CREATED);
        }
        catch (MovieAlreadyExistsException e) {
            responseEntity=new ResponseEntity<String>("MOVIE ALREADY EXISTS",HttpStatus.CONFLICT);
        }
        catch (NoSuchElementException e){
            responseEntity=new ResponseEntity<String>("MOVIE ALREADY EXISTS",HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

//    @PostMapping()
//    public ResponseEntity<?> addMultiple()

    @PutMapping("movie/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable("id") int movieId, @RequestBody String comments){
        ResponseEntity responseEntity;
        try{
            Movie updatedMovie=movieService.updateMovie(movieId,comments);
            responseEntity=new ResponseEntity<Movie>(updatedMovie, HttpStatus.CREATED);
            }
        catch (MovieNotFoundException e) {
            responseEntity=new ResponseEntity<String>("MOVIE NOT FOUND",HttpStatus.CONFLICT);
        }
        catch (NoSuchElementException e){
            responseEntity=new ResponseEntity<String>("MOVIE NOT FOUND",HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping(value = "movie")
    public ResponseEntity<?> getAllMovies(){
        List<Movie> movieList = movieService.getAllMovies();
        ResponseEntity responseEntity=new ResponseEntity< List<Movie> >(movieList, HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("movie/{title}")
    public ResponseEntity<?> searchMovie(@PathVariable("title") String movieTitle){
        ResponseEntity responseEntity;
        try{
            List<Movie> searchedMovieList=movieService.searchMovie(movieTitle);
            responseEntity=new ResponseEntity< List<Movie>>(searchedMovieList, HttpStatus.CREATED);
        }
        catch (MovieNotFoundException e) {
            responseEntity=new ResponseEntity<String>("MOVIE NOT FOUND",HttpStatus.CONFLICT);
        }
        catch (NoSuchElementException e){
            responseEntity=new ResponseEntity<String>("MOVIE NOT FOUND",HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
//    @GetMapping("/searchQuery/{title}")
//    public ResponseEntity<?> searchMovieByQuery(@PathVariable("title") String movieTitle){
//        Movie searchedMovie=movieService.getMovieByTitle(movieTitle);
//        ResponseEntity responseEntity=new ResponseEntity<Movie>(searchedMovie, HttpStatus.OK);
//        return responseEntity;
//    }

    @DeleteMapping("movie/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable("movieId") int movieId){
        ResponseEntity responseEntity;
        try{
            movieService.deleteMovie(movieService.searchMovieById(movieId));
            responseEntity=new ResponseEntity<String>("Successfully deleted ", HttpStatus.OK);
            return responseEntity;
        }
        catch (MovieNotFoundException e) {
            responseEntity=new ResponseEntity<String>("MOVIE NOT FOUND",HttpStatus.CONFLICT);
        }
        catch (NoSuchElementException e){
            responseEntity=new ResponseEntity<String>("MOVIE NOT FOUND",HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
