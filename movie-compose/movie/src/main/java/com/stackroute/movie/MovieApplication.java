package com.stackroute.movie;

//import com.stackroute.movie.service.MovieServiceImpl2;
import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.repository.MovieRepository;
import com.stackroute.movie.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class MovieApplication implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {
//
//
//	@Bean
//	public CommandLineRunner initData(MongoOperations mongo) {
//		return (String... args) -> {
//			mongo.dropCollection(Movie.class);
//			mongo.createCollection(Movie.class);
//			getMovies().forEach(mongo::save);
//		};
//	}
//	private List<Movie> getMovies() {
//		Properties yaml = loadMoviesYaml();
//		MapConfigurationPropertySource source = new MapConfigurationPropertySource(yaml);
//		return new Binder(source).bind("Movies", Bindable.listOf(Movie.class)).get();
//	}
//	private Properties loadMoviesYaml() {
//		YamlPropertiesFactoryBean properties = new YamlPropertiesFactoryBean();
//		properties.setResources(new ClassPathResource("Movies.yml"));
//		return properties.getObject();
//	}
//
	@Autowired
	private MovieRepository movieRepository;
	private static final Logger LOG
			= Logger.getLogger(String.valueOf(MovieServiceImpl.class));

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//    LOG.info("Adding default values");
		Movie movie=new Movie(1,"Devdas1","Telugu1","NaniAL", LocalDate.of(2018,9,10));
		movieRepository.save(movie);
		movie=new Movie(2,"Devdas2","Telugu2","NagarjunaAL", LocalDate.of(2018,9,10));
		movieRepository.save(movie);
	}
//    @EventListener
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        LOG.info("Adding default values");
//        Movie movie=new Movie(1,"Devdas1","Telugu1","NaniEL", LocalDate.of(2018,9,10));
//        movieRepository.save(movie);
//        movie=new Movie(2,"Devdas2","Telugu2","NagarjunaEL", LocalDate.of(2018,9,10));
//        movieRepository.save(movie);
//    }

	@Override
	public void run(String...args) throws Exception {
		//    LOG.info("Increment counter");
		Movie movie=new Movie(3,"Devdas3","Telugu3","NaniCLR", LocalDate.of(2018,9,10));
		movieRepository.save(movie);
		movie=new Movie(4,"Devdas4","Telugu4","NagarjunaCLR", LocalDate.of(2018,9,10));
		movieRepository.save(movie);
	}


	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}
}
