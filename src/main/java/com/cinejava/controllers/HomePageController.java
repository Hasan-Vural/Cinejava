package com.cinejava.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cinejava.Main;
import com.cinejava.models.Movie;
import com.cinejava.services.implementations.MoviesService;
import com.cinejava.services.interfaces.IMoviesService;
import com.cinejava.singletons.MovieInstanceSingleton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HomePageController {
    @FXML
    private HBox movieSlider;
    private final String setReservationButtonText = "Rezervasyon Yap";
    private final int movieBoxWidth = 300;
    private final int movieBoxHeight = 600;
    private int currentIndex = 0;
    private final int movieCount;

    private List<VBox> movieBoxes = new ArrayList<>();
    private final IMoviesService moviesService;

    public HomePageController() {
        moviesService = new MoviesService();

        List<Movie> movies = moviesService.getAll().subList(0, 3);
        movieCount = moviesService.getAll().size();

        for (int i = 0; i < movies.size(); i++) {
            movieBoxes.add(createMovieBox(movies.get(i)));
        }
    }

    @FXML
    public void initialize() {
        Button button1 = createNavigationButton("<", -1);
        Button button2 = createNavigationButton(">", +1);


        movieSlider.setSpacing(20);
        movieSlider.setStyle("-fx-padding: 20; -fx-alignment: center;");

        movieSlider.getChildren().add(button1);

        movieBoxes.forEach(x -> movieSlider.getChildren().add(x));

        movieSlider.getChildren().add(button2);
    }

    private VBox createMovieBox(Movie movie) {
        VBox vbox = new VBox();
        vbox.setMaxSize(movieBoxWidth, movieBoxHeight);
        vbox.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-radius: 5;");

        // Resim ve bilgi alanını üst üste koyan StackPane
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(movieBoxWidth, 525); // Resmin boyutuna uygun

        // Film Resmi
        ImageView imageView = createMovieImageView();

        // Bilgi Alanı
        HBox overlayBox = createMovieOverlayBox(movie.getName(), movie.getGenres(), movie.getTicketPrice());
        overlayBox.setPrefHeight(100); // Bilgi alanının yüksekliğini sabit tut

        // StackPane içine ekle
        stackPane.getChildren().addAll(imageView, overlayBox);
        StackPane.setAlignment(overlayBox, Pos.BOTTOM_CENTER); // Bilgi alanını alt kısma hizala

        // Rezervasyon Butonu
        Button reserveButton = createMovieReserveButton(movie.getId());

        // Ana kutuyu oluştur
        vbox.getChildren().addAll(stackPane, reserveButton);

        return vbox;
    }

    private ImageView createMovieImageView() {
        ImageView imageView = new ImageView();
        Image image = new Image(getClass().getResource("/images/placeholder.jpg").toExternalForm());
        imageView.setImage(image);
        imageView.setFitHeight(500);
        imageView.setFitWidth(movieBoxWidth);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    private HBox createMovieOverlayBox(String movieName, List<String> movieGenres, int movieTicketPrice) {
        HBox overlayBox = new HBox();
        overlayBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");
        overlayBox.setAlignment(Pos.CENTER_LEFT);
        overlayBox.setMaxHeight(100);
    
        // Sol Kısım: Film Adı ve Türler
        VBox leftBox = new VBox();
        Label movieNameLabel = new Label(movieName);
        movieNameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
    
        HBox genresBox = new HBox();
        genresBox.setSpacing(10);
        
        VBox.setMargin(genresBox, new javafx.geometry.Insets(15, 0, 0, 0));

        for (String genre : movieGenres) {
            Label genreLabel = new Label(genre);
            genreLabel.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-padding: 3 10; -fx-background-radius: 11;");
            genresBox.getChildren().add(genreLabel);
        }
    
        leftBox.getChildren().addAll(movieNameLabel, genresBox);
    
        // Sağ Kısım: Fiyat
        VBox rightBox = new VBox();
        Label priceLabel = new Label(String.format("$%d", movieTicketPrice));
        priceLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-size: 18px; -fx-font-weight: bold;");
        rightBox.getChildren().addAll(priceLabel);
    
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
    
        overlayBox.getChildren().addAll(leftBox, spacer, rightBox);

        HBox.setHgrow(overlayBox, Priority.ALWAYS);
        return overlayBox;
    }
    

    private Button createMovieReserveButton(long movieId) {
        Button reserveButton = new Button(setReservationButtonText);
        reserveButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
        reserveButton.setPrefWidth(movieBoxWidth);
        reserveButton.setPrefHeight(75);
        reserveButton.setOnMouseEntered(event -> reserveButton.setCursor(javafx.scene.Cursor.HAND));
        reserveButton.setOnMouseExited(event -> reserveButton.setCursor(javafx.scene.Cursor.DEFAULT));

        reserveButton.getProperties().put("movieId", movieId);

        reserveButton.setOnMouseClicked(event -> 
            onMovieDetailButtonClick((long) reserveButton.getProperties().get("movieId"))
        );

        return reserveButton;
    }

    private Button createNavigationButton(String text, int direction) {
        Button button = new Button(text);
        button.setPrefSize(50, 50);
        button.setStyle(
            "-fx-background-color: #3498db; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 18px; " +
            "-fx-font-weight: bold; " +
            "-fx-background-radius: 25; " +
            "-fx-border-radius: 25; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0.5, 0, 4);"
        );
        button.setOnMouseEntered(event -> button.setCursor(javafx.scene.Cursor.HAND));
        button.setOnMouseExited(event -> button.setCursor(javafx.scene.Cursor.DEFAULT));
        button.setOnAction(event -> navigate(direction));
        return button;
    }
    
    private void navigate(int direction) {
        currentIndex = (currentIndex + direction + movieCount) % movieCount;
        updateVisibleMovies();
    }

    private void updateVisibleMovies() {
        int prevIndex = (currentIndex - 1 + movieCount) % movieCount;
        int nextIndex = (currentIndex + 1) % movieCount;

        VBox prevBox = createMovieBox(moviesService.getByIndex(prevIndex));
        VBox currentBox = createMovieBox(moviesService.getByIndex(currentIndex));
        VBox nextBox = createMovieBox(moviesService.getByIndex(nextIndex));

        Button button1 = createNavigationButton("<", -1);
        Button button2 = createNavigationButton(">", +1);

        movieSlider.getChildren().clear();
        movieSlider.getChildren().addAll(button1, prevBox, currentBox, nextBox, button2);
    }

    @FXML
    private void onMovieDetailButtonClick(long movieId) {
        try {

            Optional<Movie> movie = moviesService.get(movieId);

            MovieInstanceSingleton.getInstance().setMovie(movie.get());
            
            Main.setRoot("MoviePage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
