package com.stackroute.movie.repository;

import com.stackroute.movie.domain.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


//This is integrated test we need DB server
@RunWith(SpringRunner.class)
@DataMongoTest
//@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    @Before
    public void setUp()
    {
        movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieComments("comment1");
        movie.setMovieLanguage("telugu1");
        movie.setMovieTitle("dev1");
    }

    @After
    public void tearDown(){

        movieRepository.deleteAll();
    }
    
    @Test
    public void testInsertMovie(){
     movieRepository.insert(movie);
     Movie fetchMovie = movieRepository.findById(movie.getMovieId()).get();
        Assert.assertEquals(1,fetchMovie.getMovieId());
    }

    @Test
    public void testInsertMovieFailure(){
        Movie testMovie1 = new Movie(1,"dev1","telugu1","comment1",LocalDate.now());
        movieRepository.insert(testMovie1);
        Movie fetchMovie = movieRepository.findById(testMovie1.getMovieId()).get();
        Assert.assertNotSame(testMovie1,fetchMovie);
    }
    @Test
    public void testexistsById(){
        movieRepository.insert(movie);
        Assert.assertEquals(true,movieRepository.existsById(movie.getMovieId()));
    }

    @Test
    public void testexistsByIdFailure(){
        Movie testMovie1 = new Movie(1,"dev1","telugu1","comment1",LocalDate.now());
        movieRepository.insert(movie);
        Assert.assertEquals(false,movieRepository.existsById(19999));
    }
    @Test
    public void testFindById(){
        movieRepository.insert(movie);
        Movie fetchMovie = movieRepository.findById(movie.getMovieId()).get();
        Assert.assertEquals(1,fetchMovie.getMovieId());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindByIdFailure(){
        Movie fetchMovie = movieRepository.findById(10000).get();
        Assert.assertNotSame(movie,fetchMovie);
    }
    @Test
    public void testDeleteMovie(){
        movieRepository.insert(movie);
        Movie fetchMovie = movieRepository.findById(movie.getMovieId()).get();
        Assert.assertEquals(1,fetchMovie.getMovieId());
    }
    @Test
    public void testDeleteMovieFailure(){
        Movie testMovie1 = new Movie(1,"dev1","telugu1","comment1",LocalDate.now());
        movieRepository.insert(testMovie1);
        Movie fetchMovie = movieRepository.findById(testMovie1.getMovieId()).get();
        Assert.assertNotSame(testMovie1,fetchMovie);
    }
    @Test
    public void testgetByMovieTitle(){
        movieRepository.insert(movie);
        List<Movie> fetchMovie = movieRepository.getMovieByMovieTitle(movie.getMovieTitle());
        Assert.assertEquals(1,fetchMovie.get(0).getMovieId());
    }
    @Test
    public void testgetByMovieTitleFailure(){
        Movie testMovie1 = new Movie(1,"dev1","telugu1","comment1",LocalDate.now());
        movieRepository.insert(testMovie1);
        List<Movie> fetchMovie = movieRepository.getMovieByMovieTitle(testMovie1.getMovieTitle());
        Assert.assertNotSame(testMovie1,fetchMovie.get(0));
    }
    @Test
    public void testGetAllMovie(){
        Movie testMovie2 = new Movie(2,"dev2","telugu2","comment2",LocalDate.now());
        Movie testMovie3 = new Movie(3,"dev3","telugu3","comment3",LocalDate.now());
        movieRepository.insert(testMovie2);
        movieRepository.insert(testMovie3);
        System.out.println(movieRepository.findAll());
        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("dev2",list.get(0).getMovieTitle());
    }
    @Test
    public void updateMovieTest() {
        movieRepository.insert(movie);
        Movie movie1 = movieRepository.findById(movie.getMovieId()).get();
        movie1.setMovieComments("Sending emails @ 10AM");
        movieRepository.save(movie1);
        Assert.assertEquals("Sending emails @ 10AM", movie1.getMovieComments());
    }
    @Test
    public void updateMovieTestFailure() {
        movieRepository.insert(movie);
        Movie movie1 = movieRepository.findById(movie.getMovieId()).get();
        movie1.setMovieComments("Sending emails @ 10AM");
        movieRepository.save(movie1);
        Assert.assertNotSame(movie, movie1);
    }
}
