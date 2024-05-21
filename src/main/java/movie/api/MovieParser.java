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
 * The MovieParser class provides methods to parse JSON data obtained from TMDB API into Movie objects.
 */
public class MovieParser {

    /**
     * Parses JSON data representing movie details into a list of Movie objects.
     *
     * @param results JSON array containing movie data
     * @return A list of Movie objects parsed from the JSON data
     */
    public List<Movie> parseMovieData(JSONArray results) {
        List<Movie> moviesParsed = new ArrayList<>();

        for (int i = 0; i < results.length(); i++) {
            JSONObject movieObject = results.getJSONObject(i);
            Integer id = movieObject.optInt("id");
            String title = movieObject.optString("title");
            String overview = movieObject.optString("overview");
            Integer popularity = movieObject.optInt("popularity");
            String releaseDate = movieObject.optString("release_date");
            String posterPath = movieObject.optString("poster_path", "");
            String backdropPath = movieObject.optString("backdrop_path", "");

            JSONArray genreIdsArray = movieObject.getJSONArray("genre_ids");
            List<Integer> genreIds = new ArrayList<>();
            for (int j = 0; j < genreIdsArray.length(); j++) {
                genreIds.add(genreIdsArray.getInt(j));
            }

            try {
                // Fetch credits for the movie
                HttpRequest creditsRequest = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.themoviedb.org/3/movie/" + id + "/credits?language=en-US"))
                        .header("accept", "application/json")
                        .header("Authorization",
                                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzODQwODUzZjkxN2EwYmU4ZDk0NDk2YmU4NGYxODUwNCIsInN1YiI6IjY2MmMzNDI1OTU2NjU4MDEyNjFlMWQ4ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QuXwjQM0L2YopTeYuINH9l9HaiqJhLez_sJZD0c1zik")
                        .method("GET", HttpRequest.BodyPublishers.noBody()).build();
                HttpResponse<String> creditsResponse = HttpClient.newHttpClient().send(creditsRequest,
                        HttpResponse.BodyHandlers.ofString());

                // Parse credits JSON response
                JSONObject creditsJson = new JSONObject(creditsResponse.body());
                JSONArray crew = creditsJson.getJSONArray("crew");

                // Find the director from the crew list
                String director = "";
                for (int j = 0; j < crew.length(); j++) {
                    JSONObject crewMember = crew.getJSONObject(j);
                    if (crewMember.getString("job").equals("Director")) {
                        director = crewMember.getString("name");
                        break;
                    }
                }

                // Add movie to the list
                moviesParsed.add(new Movie(title, posterPath, backdropPath, releaseDate, popularity, overview,
                        director, id, genreIds));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return moviesParsed;
    }
}