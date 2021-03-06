package com.stackroute.movie.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exception.MovieAlreadyExistsException;
import com.stackroute.movie.exception.MovieNotFoundException;
import com.stackroute.movie.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

//find out difference between @Mock and @MockBean

//@RunWith(MockitoJUnitRunner.class)
//@DataMongoTest
@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {


    @Autowired
    private MockMvc mockMvc;
    private Movie movie;

    @MockBean
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    private List<Movie> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieComments("Nag");
        movie.setMovieLanguage("telugu");
        movie.setMovieTitle("devdas");
    //    movie.setMovieReleaseDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        list = new ArrayList();

        list.add(movie);
    }

    @Test
    public void insertMovie() throws Exception {
        when(movieService.insertMovie(anyObject())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void insertMovieFailure() throws Exception {
        System.out.println("\n\n\n"+asJsonString(movie));
        when(movieService.insertMovie(anyObject())).thenThrow(MovieAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void updateMovie() throws Exception {
        when(movieService.updateMovie(anyInt(),anyString())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/movie/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void updateMovieFailure() throws Exception {
        System.out.println("\n\n\n"+asJsonString(movie));
        when(movieService.updateMovie(anyInt(),anyString())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/movie/1099")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void searchMovie() throws Exception {
        List<Movie> movieList=movieService.getAllMovies();
        when(movieService.searchMovie(anyObject())).thenReturn(movieList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/any")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void searchMovieFailure() throws Exception {
        System.out.println("\n\n\n"+asJsonString(movie));
        List<Movie> movieList=movieService.getAllMovies();
        when(movieService.searchMovie("any")).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/ANY")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void deleteMovie() throws Exception {
        when(movieService.deleteMovie(anyObject())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void deleteMovieFailure() throws Exception {
        System.out.println("\n\n\n"+asJsonString(movie));
        when(movieService.deleteMovie(anyObject())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/1099")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getAllMovie() throws Exception {
        when(movieService.getAllMovies()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie")
        .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
