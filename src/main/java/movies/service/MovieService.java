package movies.service;

import movies.data.MovieRepository;
import movies.model.Genre;
import movies.model.Movie;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findMoviesByGenre(Genre genre) {

        return movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre() == genre).collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByLength(int length) {

        return movieRepository.findAll().stream()
                .filter(movie -> movie.getMinutes() <= length).collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByName(String name) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getName().toLowerCase().contains( name.toLowerCase() )).collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByDirector(String director) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getDirector().toLowerCase().contains( director.toLowerCase() )).collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByTemplate(Movie template) {
        if (template.getMinutes() != null && template.getMinutes() < 0) {
            throw new IllegalArgumentException();
        }

        Predicate<Movie> isName = n -> n.getName().toLowerCase().contains(
                Optional.ofNullable(template.getName()).orElse("").toLowerCase());
        Predicate<Movie> isMinutes = m -> m.getMinutes() <= Optional.ofNullable(template.getMinutes()).orElse(m.getMinutes());
        Predicate<Movie> isGenre = g -> g.getGenre().toString().contains(
                Optional.ofNullable(template.getGenre()).orElse(Genre.EMPTY).toString());
        Predicate<Movie> isDirector = d -> d.getDirector().toLowerCase().contains(
                Optional.ofNullable(template.getDirector()).orElse("").toLowerCase());

        return movieRepository.findAll().stream().filter(isName.and(isMinutes).and(isGenre).and(isDirector)).collect(Collectors.toList());
    }
}
