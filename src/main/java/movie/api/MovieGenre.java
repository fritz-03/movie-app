package movie.api;

import movie.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * The MovieGenre class provides methods to fetch movies by genre from the TMDB API.
 */
public class MovieGenre {

    /**
     * Fetches movies of a specific genre from the TMDB API.
     *
     * @param genreName The name of the genre to fetch movies for.
     * @return A list of Movie objects belonging to the specified genre.
     */
    public List<Movie> fetchGenreMovie(String genreName) {
        List<Movie> movies = new ArrayList<>();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.themoviedb.org/3/genre/movie/list?language=en"))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzODQwODUzZjkxN2EwYmU4ZDk0NDk2YmU4NGYxODUwNCIsInN1YiI6IjY2MmMzNDI1OTU2NjU4MDEyNjFlMWQ4ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QuXwjQM0L2YopTeYuINH9l9HaiqJhLez_sJZD0c1zik")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray genresArray = jsonResponse.getJSONArray("genres");

            // Process each genre
            for (int i = 0; i < genresArray.length(); i++) {
                JSONObject genreObject = genresArray.getJSONObject(i);
                String genreNameFromApi = genreObject.getString("name");

                // Check if the genre name matches the desired genre
                if (genreName.equalsIgnoreCase(genreNameFromApi)) {
                    int genreId = genreObject.getInt("id");

                    // Now fetch movies of this genre using the genreId
                    List<Movie> actionMovies = fetchMoviesByGenre(genreId);
                    movies.addAll(actionMovies);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return movies;
    }

    private List<Movie> fetchMoviesByGenre(int genreId) {
        List<Movie> movies = new ArrayList<>();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.themoviedb.org/3/discover/movie?with_genres=" + genreId))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzODQwODUzZjkxN2EwYmU4ZDk0NDk2YmU4NGYxODUwNCIsInN1YiI6IjY2MmMzNDI1OTU2NjU4MDEyNjFlMWQ4ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QuXwjQM0L2YopTeYuINH9l9HaiqJhLez_sJZD0c1zik")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response to get movie details
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray results = jsonResponse.getJSONArray("results");

            MovieParser movieParser = new MovieParser();
            movies.addAll(movieParser.parseMovieData(results));

        } catch (Exception e) {
            System.out.println(e);
        }
        return movies;
    }
}

