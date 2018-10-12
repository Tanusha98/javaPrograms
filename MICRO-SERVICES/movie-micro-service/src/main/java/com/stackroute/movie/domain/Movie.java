package com.stackroute.movie.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.Entity;
//import javax.persistence.Id;
import java.time.LocalDate;

//@Entity
@Document
public class Movie {
    @Id
    private int movieId;
    private String movieTitle;
    private String movieLanguage;
    private String movieComments;
//    private LocalDate movieReleaseDate;

    public Movie(int movieId, String movieTitle, String movieLanguage, String movieComments, LocalDate movieReleaseDate) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieLanguage = movieLanguage;
        this.movieComments = movieComments;
//        this.movieReleaseDate = movieReleaseDate;
    }

    public Movie() {
    //    setMovieReleaseDate(LocalDate.now());
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieComments() {
        return movieComments;
    }

    public void setMovieComments(String movieComments) {
        this.movieComments = movieComments;
    }

//    public LocalDate getMovieReleaseDate() {
//        return movieReleaseDate;
//    }

//    public void setMovieReleaseDate(LocalDate movieReleaseDate) {
//        this.movieReleaseDate = movieReleaseDate;
//    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieLanguage='" + movieLanguage + '\'' +
                ", movieComments='" + movieComments + '\'' +
//                ", movieReleaseDate=" + movieReleaseDate +
                '}';
    }
}
