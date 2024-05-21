package org.fritz.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import movie.Movie;
import movie.api.MovieGenre;
import movie.api.MovieNowPlaying;
import movie.api.MovieSearch;
import movie.api.MovieTrending;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Controller {

    @FXML
    private Label headerLabel;

    @FXML
    private ImageView imagePoster1;

    @FXML
    private ImageView imagePoster2;

    @FXML
    private ImageView imagePoster3;

    @FXML
    private ImageView imagePoster4;

    @FXML
    private ImageView imagePoster5;

    @FXML
    private ImageView imagePoster6;

    @FXML
    private ImageView imagePoster7;

    @FXML
    private ImageView imagePoster8;

    @FXML
    private TextField searchMovieField;

    @FXML
    private ImageView[] imagePosters = new ImageView[8];

    MovieTrending movieTrending = new MovieTrending();
    List<Movie> movies = movieTrending.fetchTrendingMovies();

    @FXML
    void initialize() {
        try {
            // Initialize imagePosters array and movies list here
            imagePosters = new ImageView[]{imagePoster1, imagePoster2, imagePoster3, imagePoster4,
                    imagePoster5, imagePoster6, imagePoster7, imagePoster8};

            headerLabel.setText("Now Playing Movies");

            // Fetch now playing movies
            MovieNowPlaying movieNowPlaying = new MovieNowPlaying();
            List<Movie> moviesPlaying = movieNowPlaying.fetchNowPlayingMovie();

            addPosters(randomize(moviesPlaying));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void animationButton(ActionEvent event) {
        filterByGenre("Animation");
    }

    @FXML
    void crimeButton(ActionEvent event) {
        filterByGenre("Crime");
    }

    @FXML
    void familyButton(ActionEvent event) {
        filterByGenre("Family");
    }

    @FXML
    void fantasyButton(ActionEvent event) {
        filterByGenre("Fantasy");
    }

    @FXML
    void historyButton(ActionEvent event) {
        filterByGenre("History");
    }

    @FXML
    void horrorButton(ActionEvent event) {
        filterByGenre("Horror");
    }

    @FXML
    void musicButton(ActionEvent event) {
        filterByGenre("Music");
    }

    @FXML
    void mysteryButton(ActionEvent event) {
        filterByGenre("Mystery");
    }

    @FXML
    void scifiButton(ActionEvent event) {
        filterByGenre("Science Fiction");
    }

    @FXML
    void thrillerButton(ActionEvent event) {
        filterByGenre("Thriller");
    }

    @FXML
    void warButton(ActionEvent event) {
        filterByGenre("War");
    }

    @FXML
    void westernButton(ActionEvent event) {
        filterByGenre("Western");
    }

    @FXML
    void actionButton(ActionEvent event) {
        filterByGenre("Action");
    }

    @FXML
    void adventureButton(ActionEvent event) {
        filterByGenre("Adventure");
    }

    @FXML
    void comedyButton(ActionEvent event) {
        filterByGenre("Comedy");
    }

    @FXML
    void dramaButton(ActionEvent event) {
        filterByGenre("Drama");
    }
    @FXML
    void romanceButton(ActionEvent event) {
        filterByGenre("Romance");
    }

    @FXML
    void searchButton(ActionEvent event) {
        try {
            if (searchMovieField != null) {
                String searched = searchMovieField.getText();
                MovieSearch movieSearch = new MovieSearch();
                List<Movie> movies = movieSearch.fetchSearchMovie(searched);
                addPosters(movies);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void trendingButton(ActionEvent event) {
        try {
            headerLabel.setText("Trending Movies");
            movies = movieTrending.fetchTrendingMovies();
            addPosters(randomize(movies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private void filterByGenre(String findGenre) {
        headerLabel.setText(findGenre + " Movies");
        MovieGenre movieGenre = new MovieGenre();
        List<Movie> genre = movieGenre.fetchGenreMovie(findGenre);
        addPosters(randomize(genre));
    }

    private List<Movie> randomize(List<Movie> movieGenre) {
        Collections.shuffle(movieGenre, new Random());
        return movieGenre;
    }

    private void handleImageClick(MouseEvent event) {
        try {
            ImageView clickedImageView = (ImageView) event.getSource();
            Movie clickedMovie = (Movie) clickedImageView.getUserData();
            System.out.println(clickedMovie.getTitle());

            Stage currentStage = (Stage) clickedImageView.getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene-2.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1128, 682);
            OverviewController overview = fxmlLoader.getController();

            // Pass movie details to OverviewController
            overview.setMovieDetails(clickedMovie.getTitle(), clickedMovie.getDirector(), clickedMovie.getOverview(),
            clickedMovie.getReleaseYear(), clickedMovie.getPosterPath(), clickedMovie.getBackdropPath());

            Stage stage = new Stage();
            stage.setTitle("Overview");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addPosters(List<Movie> movies) {
        clearPosters();

        int numberOfPosters = Math.min(movies.size(), 8);
        for (int i = 0; i < numberOfPosters; i++) {
            Movie movie = movies.get(i);
            String imagePath = movie.getPosterPath();
            Image image = new Image("https://image.tmdb.org/t/p/w500" + imagePath);
            imagePosters[i].setImage(image);
            imagePosters[i].setUserData(movie);
            imagePosters[i].setOnMouseClicked(this::handleImageClick);
            addHoverAnimation(imagePosters[i]);
        }
    }

    private void clearPosters() {
        for (ImageView imageView : imagePosters) {
            imageView.setImage(null);
            imageView.setUserData(null);
            imageView.setOnMouseClicked(null);
        }
    }

    private void addHoverAnimation(ImageView imageView) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), imageView);
        scaleUp.setToX(1.1);
        scaleUp.setToY(1.1);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), imageView);
        scaleDown.setToX(1);
        scaleDown.setToY(1);

        imageView.setOnMouseEntered(event -> scaleUp.play());
        imageView.setOnMouseExited(event -> scaleDown.play());
    }
}
