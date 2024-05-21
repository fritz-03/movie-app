package org.fritz.app;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;


import java.io.IOException;

public class OverviewController {

    @FXML
    private Label directorLabel;

    @FXML
    private ImageView imageBackdrop;

    @FXML
    private ImageView imagePoster;

    @FXML
    private Label overviewLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label yearLabel;


    public void setMovieDetails(String title, String director, String overview, String year, String posterImage, String backdropImage) {
        titleLabel.setText(title);
        directorLabel.setText(director);
        overviewLabel.setText(overview);
        yearLabel.setText(year);
        imagePoster.setImage(new Image("https://image.tmdb.org/t/p/w500" + posterImage));
        imageBackdrop.setImage(new Image("https://image.tmdb.org/t/p/original" + backdropImage));
    }

    @FXML
    void backButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene-1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1128, 682);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        currentStage.close();

        Stage stage = new Stage();
        stage.setTitle("Final Project!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
