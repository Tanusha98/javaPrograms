package com.stackroute.movie.repository;

import com.stackroute.movie.domain.Movie;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie,Integer> {
//   @Query(value="select * from MOVIE o where o.MOVIE_TITLE=:movieTitle",nativeQuery = true)
    List<Movie> getMovieByMovieTitle(String movieTitle );
}
