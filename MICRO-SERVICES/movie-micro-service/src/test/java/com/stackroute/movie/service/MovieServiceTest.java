package com.stackroute.movie.service;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exception.MovieAlreadyExistsException;
import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exception.MovieAlreadyExistsException;
import com.stackroute.movie.exception.MovieNotFoundException;
import com.stackroute.movie.repository.MovieRepository;
import com.stackroute.movie.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    Movie movie;

    //Create a mock for MovieRepository
    @Mock//test double
    MovieRepository movieRepository;

    //Inject the mocks as dependencies into MovieServiceImpl
    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> list= null;


    @Before
    public void setUp() throws MovieAlreadyExistsException{
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieComments("comment1");
        movie.setMovieLanguage("telugu1");
        movie.setMovieTitle("dev1");
        list = new ArrayList<>();
        list.add(movie);

    }

    @Test
    public void insertMovieTestSuccess() throws MovieAlreadyExistsException {

        when(movieRepository.insert((Movie)any())).thenReturn(movie);
        Movie insertdMovie = movieService.insertMovie(movie);
        System.out.println(movieService.getAllMovies());
        System.out.println("insertdMovie" + insertdMovie);
        Assert.assertEquals(movie,insertdMovie);
        //verify here verifies that movieRepository insert method is only called once
        verify(movieRepository,times(1)).insert(movie);
      
    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void insertMovieTestFailure() throws MovieAlreadyExistsException {
        when(movieRepository.insert((Movie)any())).thenReturn(null);
        Movie insertdMovie = movieService.insertMovie(movie);
        Assert.assertEquals(movie,insertdMovie);
//add verify
 //      doThrow(new MovieAlreadyExistsException("movie exists")).when(movieRepository).findById(eq(101));
 //      movieService.insertMovie(movie);
        verify(movieRepository,times(1)).insert(movie);
//        if(insertdMovie.equals(null))
//        throw(new MovieAlreadyExistsException("movie exists"));

    }
    @Test//(expected = NoSuchElementException.class)
    public void updateMovieTestSuccess() throws MovieNotFoundException,MovieAlreadyExistsException {

        when(movieRepository.insert((Movie)any())).thenReturn(movie);
        movieService.insertMovie(movie);
        when(movieRepository.existsById(movie.getMovieId())).thenReturn(true);
        Optional<Movie> optionalMovie=Optional.of(movie);
        when(movieRepository.findById(movie.getMovieId())).thenReturn(optionalMovie);
        Movie updatedMovie = movieService.updateMovie(movie.getMovieId(),"updated");
        Assert.assertEquals(updatedMovie.getMovieComments(),"updated");
//        //verify here verifies that movieRepository insert method is only called once
        verify(movieRepository,times(2)).insert(movie);
        verify(movieRepository,times(2)).existsById(movie.getMovieId());
        verify(movieRepository,times(1)).findById(movie.getMovieId());
    }

    @Test(expected = MovieNotFoundException.class)
    public void updateMovieTestFailure() throws MovieNotFoundException {
        //when(movieRepository.insert((Movie)any())).thenReturn(null);
        Movie updatedMovie = movieService.updateMovie(movie.getMovieId(),movie.getMovieComments());
        Assert.assertEquals(movie,updatedMovie);
//add verify
        //      doThrow(new MovieAlreadyExistsException("movie exists")).when(movieRepository).findById(eq(101));
        //      movieService.insertMovie(movie);
        //       verify(movieRepository,times(1)).insert(movie);
//        if(insertdMovie.equals(null))
//        throw(new MovieAlreadyExistsException("movie exists"));

    }
    @Test//(expected = NoSuchElementException.class)
    public void deleteMovieTestSuccess() throws MovieNotFoundException,MovieAlreadyExistsException {

        when(movieRepository.insert((Movie)any())).thenReturn(movie);
        movieService.insertMovie(movie);
        when(movieRepository.existsById(movie.getMovieId())).thenReturn(true);
//        if (movieRepository.existsById(movie.getMovieId()))
//            when(movieService.deleteMovie(movie)).thenReturn(true);
        Assert.assertEquals(true,movieService.deleteMovie(movie));
//        //verify here verifies that movieRepository insert method is only called once
        verify(movieRepository,times(1)).delete(movie);
        verify(movieRepository,times(2)).existsById(movie.getMovieId());
    }

    @Test(expected = MovieNotFoundException.class)
    public void deleteMovieTestFailure() throws MovieNotFoundException {
        //when(movieRepository.insert((Movie)any())).thenReturn(null);
        when(movieRepository.existsById(movie.getMovieId())).thenReturn(false);
        movieService.deleteMovie(movie);
        Assert.assertEquals(true,movieService.deleteMovie(movie));
//add verify
        //      doThrow(new MovieAlreadyExistsException("movie exists")).when(movieRepository).findById(eq(101));
        //      movieService.insertMovie(movie);
        //       verify(movieRepository,times(1)).insert(movie);
//        if(insertdMovie.equals(null))
//        throw(new MovieAlreadyExistsException("movie exists"));

    }

    @Test
    public void searchMovie() throws MovieNotFoundException {
        List<Movie> movieList=movieRepository.findAll();
        when(movieRepository.getMovieByMovieTitle(anyString())).thenReturn(movieList);
        List<Movie> updatedMovieList = movieService.searchMovie("Rang De Basanti");
        Assert.assertSame(movieList,updatedMovieList);
        verify(movieRepository,times(2)).getMovieByMovieTitle(anyString());
    }
    @Test(expected = MovieNotFoundException.class)
    public void searchMovieFailure() throws MovieNotFoundException {
        when(movieRepository.getMovieByMovieTitle(anyString())).thenReturn(null);
        List<Movie> updatedMovie = movieService.searchMovie("sdc");
        Assert.assertSame(null,updatedMovie);
        verify(movieRepository,times(1)).getMovieByMovieTitle(anyString());
    }

    @Test
    public void getAllMovie(){

        movieRepository.insert(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movielist = movieService.getAllMovies();
        Assert.assertEquals(list,movielist);

        //add verify
    }

}
