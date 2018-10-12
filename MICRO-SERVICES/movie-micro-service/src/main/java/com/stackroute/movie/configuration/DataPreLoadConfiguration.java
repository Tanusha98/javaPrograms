package com.stackroute.movie.configuration;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.repository.MovieRepository;
import com.stackroute.movie.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.time.LocalDate;
import java.util.logging.Logger;

@Configuration
public class DataPreLoadConfiguration  implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {
    @Autowired
    private MovieRepository movieRepository;

    private static final Logger LOG
            = Logger.getLogger(String.valueOf(MovieServiceImpl.class));

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //    LOG.info("Adding default values");
        Movie movie=new Movie(1,"Devdas1","Telugu1","NaniAL", LocalDate.of(2018,9,10));
        movieRepository.insert(movie);
        movie=new Movie(2,"Devdas2","Telugu2","NagarjunaAL", LocalDate.of(2018,9,10));
        movieRepository.insert(movie);
    }
//    @EventListener
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        LOG.info("Adding default values");
//        Movie movie=new Movie(1,"Devdas1","Telugu1","NaniEL", LocalDate.of(2018,9,10));
//        movieRepository.insert(movie);
//        movie=new Movie(2,"Devdas2","Telugu2","NagarjunaEL", LocalDate.of(2018,9,10));
//        movieRepository.insert(movie);
//    }

    @Override
    public void run(String...args) throws Exception {
        //    LOG.info("Increment counter");
        Movie movie=new Movie(3,"Devdas3","Telugu3","NaniCLR", LocalDate.of(2018,9,10));
        movieRepository.insert(movie);
        movie=new Movie(4,"Devdas4","Telugu4","NagarjunaCLR", LocalDate.of(2018,9,10));
        movieRepository.insert(movie);
    }


}
