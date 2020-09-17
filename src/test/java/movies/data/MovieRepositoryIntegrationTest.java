package movies.data;

import movies.model.Genre;
import movies.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MovieRepositoryIntegrationTest {

    private MovieRepositoryJdbc movieRepositoryJdbc;
    private JdbcTemplate jdbcTemplate;
    private DriverManagerDataSource dataSource;

    @Before
    public void setUp() throws Exception {

        dataSource =
                new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        jdbcTemplate = new JdbcTemplate(dataSource);

        movieRepositoryJdbc = new MovieRepositoryJdbc(jdbcTemplate);
    }

    @Test
    public void load_all_movies() throws SQLException {

        Collection<Movie> movies = movieRepositoryJdbc.findAll();

        assertThat(movies, is(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, Genre.ACTION, "director 1"),
                        new Movie(2, "Memento", 113, Genre.THRILLER, "director 2"),
                        new Movie(3, "Matrix", 136, Genre.ACTION, "director 3")
                )
        ));
    }

    @Test
    public void load_movie_by_id() {

        Movie movie = movieRepositoryJdbc.findById(2);

        assertThat(movie, is(new Movie(2, "Memento", 113, Genre.THRILLER, "director 2")));
    }

    @Test
    public void insert_a_movie() {

        Movie movie = new Movie("Super 8", 112, Genre.THRILLER, "director 8");

        movieRepositoryJdbc.saveOrUpdate(movie);

        Movie movieFromDB = movieRepositoryJdbc.findById(4);

        assertThat(movieFromDB, is(new Movie(4, "Super 8", 112, Genre.THRILLER, "director 8")));
    }

    @After
    public void tearDown() throws Exception {

        //Remove H2 files
        final Statement statement = dataSource.getConnection().createStatement();
        statement.execute("drop all objects delete files"); //"shutdown" is also enough for mem db
    }
}