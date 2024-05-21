package movie;
import java.util.List;


import movie.api.MovieNowPlaying;

public class MovieMain {
    public static void main(String[] args) {
        MovieNowPlaying movieNowPlaying = new MovieNowPlaying();
        List<Movie> movies = movieNowPlaying.fetchNowPlayingMovie();
//
//        MovieTrending movieTrending = new MovieTrending();
//        List<Movie> movies = movieTrending.fetchTrendingMovies();

//        MovieSearch movieSearch = new MovieSearch();
//        List<Movie> movies = movieSearch.fetchSearchMovie("Lily chou chou");

//        MovieGenre movieGenre = new MovieGenre();
//        List<Movie> movies = movieGenre.fetchGenreMovie("Action");

        // Print the titles of fetched movies
        for (Movie movie : movies) {
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Poster: " + movie.getPosterPath());
            System.out.println("Backdrop: " + movie.getBackdropPath());
            System.out.println("Release Date: " + movie.getReleaseYear());
            System.out.println("Popularity: " + movie.getPopularity());
            System.out.println("Director: " + movie.getDirector());
            System.out.println("Id: " + movie.getId());
            System.out.println("Overview: " + movie.getOverview());
            System.out.println("Genre Ids: " + movie.getGenreIds());
        }

    }
}
