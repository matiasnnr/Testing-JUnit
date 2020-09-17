package movies.service;

import movies.data.MovieRepository;
import movies.model.Genre;
import movies.model.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceShould {

    MovieService movieService;
    MovieRepository movieRepository;

    @Before
    public void setUp() throws Exception {

        movieRepository = Mockito.mock(MovieRepository.class);

        Mockito.when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, Genre.ACTION, "director 1"),
                        new Movie(2, "Memento", 113, Genre.THRILLER, "director 2"),
                        new Movie(3, "There's Something About Mary", 119, Genre.COMEDY, "director 3"),
                        new Movie(4, "Super 8", 112, Genre.THRILLER, "director 4"),
                        new Movie(5, "Scream", 111, Genre.HORROR, "director 5"),
                        new Movie(6, "Home Alone", 103, Genre.COMEDY, "director 6"),
                        new Movie(7, "Matrix", 136, Genre.ACTION, "director 7")
                )
        );

        movieService = new MovieService(movieRepository);
    }

    @Test
    public void return_movies_by_genre() {

        Collection<Movie> moviesByGenre = movieService.findMoviesByGenre(Genre.COMEDY);

        assertThat(getMovieIds(moviesByGenre), is(Arrays.asList(3, 6)));

    }

    @Test
    public void return_movies_by_length() {

        Collection<Movie> moviesByLength = movieService.findMoviesByLength(119);

        assertThat(getMovieIds(moviesByLength), is(Arrays.asList(2, 3, 4, 5, 6)));
    }

    private List<Integer> getMovieIds(Collection<Movie> moviesByGenre) {
        //return moviesByGenre.stream().map(movie -> movie.getId()).collect(Collectors.toList());
        return moviesByGenre.stream().map(Movie::getId).collect(Collectors.toList());
    }

    @Test
    public void return_movies_by_name() {
        Collection<Movie> movies = movieService.findMoviesByName("super");
        assertThat(getMovieIds(movies), is(Arrays.asList(4)));
    }

    @Test
    public void return_movies_by_director() {
        Collection<Movie> movies = movieService.findMoviesByDirector("director 1");
        assertThat(getMovieIds(movies), is(Arrays.asList(1)));
    }

    //filter by template
    @Test
    public void returnMoviesByMovieTemplateWithDurationAndGenre() {
        Collection<Movie> movies =
                movieService.findMoviesByTemplate(
                        new Movie(null, 152, Genre.ACTION, null)
                );

        assertThat(getMovieIds(movies), is(Arrays.asList(1, 7)));
    }

    @Test
    public void returnMoviesByMovieTemplateWithNameAndDuration() {
        Collection<Movie> movies =
                movieService.findMoviesByTemplate(
                        new Movie("Super 8", 112, null, null)
                );

        assertThat(getMovieIds(movies), is(Collections.singletonList(4)));
    }

    @Test
    public void returnMoviesByMovieTemplateWithNameAndGenre() {
        Collection<Movie> movies =
                movieService.findMoviesByTemplate(
                        new Movie("Super 8", null, Genre.THRILLER, null)
                );

        assertThat(getMovieIds(movies), is(Collections.singletonList(4)));
    }

    @Test
    public void returnMoviesByMovieTemplateWithNameAndGenreAndDuration() {
        Collection<Movie> movies =
                movieService.findMoviesByTemplate(
                        new Movie("memento", 113, Genre.THRILLER, "director 2")
                );

        assertThat(getMovieIds(movies), is(Collections.singletonList(2)));
    }

    @Test
    public void returnMoviesByMovieTemplateWithDirector() {
        Collection<Movie> movies =
                movieService.findMoviesByTemplate(
                        new Movie(null, null, null, "director 1")
                );

        assertThat(getMovieIds(movies), is(Arrays.asList(1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void returnError_whenDurationIsNegative() {
        Collection<Movie> movies =
                movieService.findMoviesByTemplate(
                        new Movie(null, -120, null, null)
                );

        assertThat(getMovieIds(movies), is(Arrays.asList(1, 2)));
    }

}