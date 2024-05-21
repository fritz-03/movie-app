package movie.api;

import movie.Movie;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The MovieNowPlaying class provides methods to fetch currently playing movies from the TMDB API.
 */
public class MovieNowPlaying {

    /**
     * Fetches currently playing movies from the TMDB API.
     *
     * @return A list of Movie objects representing currently playing movies.
     */
    public List<Movie> fetchNowPlayingMovie() {
        List<Movie> movies = new ArrayList<>();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1"))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzODQwODUzZjkxN2EwYmU4ZDk0NDk2YmU4NGYxODUwNCIsInN1YiI6IjY2MmMzNDI1OTU2NjU4MDEyNjFlMWQ4ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QuXwjQM0L2YopTeYuINH9l9HaiqJhLez_sJZD0c1zik")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray results = jsonResponse.getJSONArray("results");

            MovieParser movieParser = new MovieParser();
            movies.addAll(movieParser.parseMovieData(results));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }
}
