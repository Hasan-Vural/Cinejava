package com.cinejava.controllers;

import java.util.stream.Collectors;

import com.cinejava.models.Movie;
import com.cinejava.singletons.MovieInstanceSingleton;
import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MoviePageController {
    @FXML
    private AnchorPane moviePane;

    @FXML
    private TabPane tabPane;

    @FXML
    private ImageView movieImage;

    @FXML
    private Label movieName;

    @FXML
    private Label movieGenres;

    @FXML
    private Label movieSynopsis;

    @FXML
    private Label movieImdbRating;


    @FXML
    public void initialize(){
        initializeMovie();

        initializeTabPane();
    }

    private void initializeMovie() {
        Movie movie = MovieInstanceSingleton.getInstance().getMovie();

        movieName.setText(movie.getName());
        movieGenres.setText(movie.getGenres().stream().collect(Collectors.joining(", ")));
        movieSynopsis.setText(movie.getSynopsis());
        movieImdbRating.setText(Double.toString(movie.getImdbRating()));

        moviePane.getChildren().add(new Label(movie.getName()));
    }

    private void initializeTabPane() {
        applyTabPaneStyles();

        String[] dates = {"1 Ocak", "2 Ocak", "3 Ocak", "4 Ocak", "5 Ocak"};
        String[] times = {"12:00", "14:30", "17:00", "19:30", "21:00"};
        int idCounter = 0;

        for (String date : dates) {
            Tab tab = new Tab(date);
            tab.setClosable(false);
            AnchorPane tabContent = new AnchorPane();

            // Saat ve butonları ekle
            for (int i = 0; i < times.length; i++) {
                Pane timePane = createTimePane(times[i], ++idCounter);
                timePane.setLayoutY(14 + i * 90);
                tabContent.getChildren().add(timePane);
            }

            tab.setContent(tabContent);
            tabPane.getTabs().add(tab);
        }
    }

    private void applyTabPaneStyles() {
        tabPane.setStyle(
            "-fx-background-color: #00A8A8; " +
            "-fx-tab-min-width: 150; " +
            "-fx-tab-max-width: 200; "
        );

        tabPane.getStylesheets().add("data:text/css," +
            ".tab-pane .tab-header-area { " +
            "  -fx-background-color: #007575; " +
            "} " +
            ".tab-pane .tab:selected { " +
            "  -fx-background-color: #00A8A8; " +
            "  -fx-text-fill: white; " +
            "  -fx-font-size: 16px; " +
            "  -fx-font-weight: bold; " +
            "  -fx-text-transform: uppercase; " +
            "} " +
            ".tab-pane .tab { " +
            "  -fx-background-color: #005050; " +
            "  -fx-text-fill: #ffffff; " +
            "  -fx-font-size: 14px; " +
            "  -fx-text-transform: uppercase; " +
            "} " +
            ".tab-pane .tab:hover { " +
            "  -fx-background-color: #008080; " +
            "} "
        );
    }

    private Pane createTimePane(String time, int index) {
        Pane timePane = new Pane();
        timePane.setPrefSize(750, 70);
        timePane.setStyle("-fx-background-color: #007575;");

        Text timeText = new Text(14, 52, time);
        timeText.setStyle("-fx-font-size: 43px; -fx-fill: white;");

        JFXButton ticketButton = new JFXButton("Bilet Al");
        ticketButton.setLayoutX(605);
        ticketButton.setLayoutY(14);
        ticketButton.setPrefSize(137, 50);
        ticketButton.setStyle("-fx-background-color: #00A8A8; -fx-text-fill: white; -fx-font-size: 18px;");
        ticketButton.setId("biletal" + index);

        ticketButton.setOnMouseEntered(event -> ticketButton.setCursor(javafx.scene.Cursor.HAND));
        ticketButton.setOnMouseExited(event -> ticketButton.setCursor(javafx.scene.Cursor.DEFAULT));

        ticketButton.setOnMouseClicked(event -> handleTicketClick(index));

        timePane.getChildren().addAll(timeText, ticketButton); 
        return timePane;
    }

    private void handleTicketClick(int index) {
        System.out.println("Bilet Al tıklandı. Seans ID: " + index);
    }
}
