package movie;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void getTitle() {
        String title = "Dune: Part Two";
        Movie movie = new Movie(title, "", "", "", 0, "", "", 0, null);
        assertEquals("Dune: Part Two", movie.getTitle());
    }

    @Test
    void getPosterPath() {
        String posterPath = "/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg";
        Movie movie = new Movie("", posterPath, "", "", 0, "", "", 0, null);
        assertEquals("/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg", movie.getPosterPath());
    }

    @Test
    void getBackdropPath() {
        String backdropPath = "/8uVKfOJUhmybNsVh089EqLHUYEG.jpg";
        Movie movie = new Movie("", "", backdropPath, "", 0, "", "", 0, null);
        assertEquals("/8uVKfOJUhmybNsVh089EqLHUYEG.jpg", movie.getBackdropPath());
    }

    @Test
    void getReleaseYear() {
        String releaseYear = "2024-03-12";
        Movie movie = new Movie("", "", "", releaseYear, 0, "", "", 0, null);
        assertEquals("2024", movie.getReleaseYear());
    }

    @Test
    void getPopularity() {
        Integer popularity = 1617;
        Movie movie = new Movie("", "", "", "", popularity, "", "", 0, null);
        assertEquals(1617, movie.getPopularity());
    }

    @Test
    void getDirector() {
        String director = "Denis Villeneuve";
        Movie movie = new Movie("", "", "", "", 0, "", director, 0, null);
        assertEquals("Denis Villeneuve", movie.getDirector());
    }

    @Test
    void getOverview() {
        String overview = "Follow the mythic journey of Paul Atreides as he unites with Chani and the Fremen while on a path of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, Paul endeavors to prevent a terrible future only he can foresee.";
        Movie movie = new Movie("", "", "", "", 0, overview, "", 0, null);
        assertEquals(overview, movie.getOverview());
    }

    @Test
    void getId() {
        Integer id = 693134;
        Movie movie = new Movie("", "", "", "", 0, "", "", id, null);
        assertEquals(693134, movie.getId());
    }

    @Test
    void getGenreIds() {
        List<Integer> ids = new ArrayList<>();
        ids.add(872);
        ids.add(12);
        Movie movie = new Movie("", "", "", "", 0, "", "", null, ids);
        List<Integer> genreIds = movie.getGenreIds();
        assertEquals(ids, genreIds);
    }
}