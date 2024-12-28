package com.cinejava.controllers;

import java.util.ArrayList;
import java.util.List;

import com.cinejava.models.Movie;
import com.cinejava.services.implementations.MoviesService;
import com.cinejava.services.interfaces.IMoviesService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomePageController {
    @FXML
    private HBox movieSlider;
    private List<VBox> movieBoxes = new ArrayList<>();
    private final IMoviesService moviesService;
    private final String setReservationButtonText = "Rezervasyon Yap";
    private final int movieBoxWidth = 300;
    private final int movieBoxHeight = 600;

    public HomePageController() {
        moviesService = new MoviesService();

        List<Movie> movies = moviesService.getAll().subList(0, 3);

        for (int i = 0; i < movies.size(); i++) {
            movieBoxes.add(createMovieBox(movies.get(i)));
        }
    }

    @FXML
    public void initialize() {
        Button button1 = createNavigationButton("<-", 0);
        Button button2 = createNavigationButton("->", 1);

        movieSlider.getChildren().add(button1);

        movieBoxes.forEach(x -> movieSlider.getChildren().add(x));

        movieSlider.getChildren().add(button2);
    }

    private VBox createMovieBox(Movie movie) {
        VBox vbox = new VBox();
        vbox.setMaxSize(movieBoxWidth, movieBoxHeight);
        vbox.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-radius: 5;");

        ImageView imageView = createMovieImageView();

        Button reserveButton = createMovieReserveButton(movie.getId());

        HBox overlayBox = createMovieOverlayBox(movie.getName(), movie.getGenres(), movie.getTicketPrice());

        vbox.getChildren().addAll(imageView, overlayBox, reserveButton);

        return vbox;
    }

    private ImageView createMovieImageView() {
        ImageView imageView = new ImageView();
        Image image = new Image(getClass().getResource("/images/placeholder.jpg").toExternalForm());
        imageView.setImage(image);
        imageView.setFitWidth(movieBoxWidth);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    private HBox createMovieOverlayBox(String movieName, List<String> movieGenres, int movieTicketPrice) {
        HBox overlayBox = new HBox();
        overlayBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");
        overlayBox.setOpacity(0.9);

        VBox leftBox = new VBox();
        Label movieNameLabel = new Label(movieName);
        movieNameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

        HBox genresBox = new HBox();
        genresBox.setSpacing(5);
        for (String genre : movieGenres) {
            Label genreLabel = new Label(genre);
            genreLabel.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-padding: 3 10; -fx-background-radius: 15;");
            genresBox.getChildren().add(genreLabel);
        }

        leftBox.getChildren().addAll(movieNameLabel, genresBox);

        Label priceLabel = new Label(String.format("$%d", movieTicketPrice));
        priceLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-size: 18px; -fx-font-weight: bold;");

        overlayBox.getChildren().addAll(leftBox, priceLabel);
        return overlayBox;
    }

    private Button createMovieReserveButton(long movieId) {
        Button reserveButton = new Button(setReservationButtonText);
        reserveButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
        return reserveButton;
    }

    private Button createNavigationButton(String text, int targetIndex) {
        Button button = new Button(text);
        button.setOnAction(event -> scrollToIndex(targetIndex));
        return button;
    }

    private void scrollToIndex(int index) {
        /* 
        if (index >= 0 && index < filmContainers.size()) {
            HBox target = filmContainers.get(index);
            target.requestFocus(); // İlgili film konteynerine odaklan
            System.out.println("Navigated to index: " + index);
        }
        */
    }
}
