package movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The Movie class represents a movie entity with various attributes.
 */
public class Movie {
    private String title;
    private String posterPath;
    private String backdropPath;
    private String releaseDate;
    private Integer popularity;
    private String director;
    private String overview;
    private Integer id;
    private List<Integer> genreIds;

    /**
     * Constructs a Movie object with the provided attributes.
     *
     * @param title        The title of the movie.
     * @param posterPath   The path to the movie's poster image.
     * @param backdropPath The path to the movie's backdrop image.
     * @param releaseDate  The release date of the movie (format: "yyyy-MM-dd").
     * @param popularity   The popularity score of the movie.
     * @param overview     A brief overview or summary of the movie.
     * @param director     The director of the movie.
     * @param id           The unique identifier of the movie.
     * @param genreIds     The list of genre IDs associated with the movie.
     */
    public Movie(String title, String posterPath, String backdropPath, String releaseDate, Integer popularity, String overview, String director, Integer id, List<Integer> genreIds) {
        this.title = title;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.director = director;
        this.overview = overview;
        this.id = id;
        this.genreIds = genreIds;
    }

    /**
     * Gets the title of the movie.
     *
     * @return The title of the movie.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the path to the movie's poster image.
     *
     * @return The poster image path.
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     * Gets the path to the movie's backdrop image.
     *
     * @return The backdrop image path.
     */
    public String getBackdropPath() {
        return backdropPath;
    }

    /**
     * Gets the release year of the movie.
     *
     * @return The release year of the movie.
     */
    public String getReleaseYear() {
        if (releaseDate == null || releaseDate.isEmpty()) {
            return ""; // Return an empty string if releaseDate is null or empty
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(releaseDate);
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            return yearFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the popularity score of the movie.
     *
     * @return The popularity score of the movie.
     */
    public Integer getPopularity() {
        return popularity;
    }

    /**
     * Gets the director of the movie.
     *
     * @return The director of the movie.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Gets the overview or summary of the movie.
     *
     * @return The overview or summary of the movie.
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Gets the unique identifier of the movie.
     *
     * @return The unique identifier of the movie.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the list of genre IDs associated with the movie.
     *
     * @return The list of genre IDs associated with the movie.
     */
    public List<Integer> getGenreIds() {
        return genreIds;
    }
}
