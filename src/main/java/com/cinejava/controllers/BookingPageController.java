package com.cinejava.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import com.cinejava.services.implementations.ReservationsService;
import com.cinejava.models.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingPageController {

    private static final String WHITE_SEAT = "src/main/resources/images/whiteseat.png";
    private static final String GREEN_SEAT = "src/main/resources/images/greenseat.png";
    private static final String RED_SEAT = "src/main/resources/images/redseat.png";

    private final ReservationsService reservationsService = new ReservationsService();

    private List<Integer> choosedList = new ArrayList<>();
    private List<Integer> reservedList = new ArrayList<>();

    @FXML
    private GridPane seatGrid;

    @FXML
    private Button confirmButton;

    private long sessionId;
    private long userId;
    private long movieId;

    public void initialize(long userId, long movieId, long sessionId) {
        this.userId = userId;
        this.movieId = movieId;
        this.sessionId = sessionId;
        renderSeats();
        setupConfirmButton();
    }

    private void initializeReservedSeats() {
        reservedList = reservationsService.getReservationsBySessionId(sessionId)
                .stream()
                .flatMap(reservation -> reservation.getReservedSeats().stream())
                .collect(Collectors.toList());
    }

    private void renderSeats() {
        initializeReservedSeats();

        seatGrid.getChildren().clear(); // Clear any existing nodes in the grid

       
        for (int seatId = 1; seatId <= 32; seatId++) {
            final int currentSeatId = seatId; // Create a final variable for the lambda expression
            ImageView seatImage = new ImageView(new Image(WHITE_SEAT));
            seatImage.setFitWidth(50);
            seatImage.setFitHeight(50);

            if (reservedList.contains(currentSeatId)) {
                seatImage.setImage(new Image(RED_SEAT));
                seatImage.setDisable(true);
            } else {
                seatImage.setOnMouseClicked(event -> handleSeatClick(seatImage, currentSeatId));
            }   

            int row = (currentSeatId - 1) / 8;
            int col = (currentSeatId - 1) % 8;
            seatGrid.add(seatImage, col, row);
        }
    }

    private void handleSeatClick(ImageView seatImage, int seatId) {
        if (seatImage.getImage().getUrl().endsWith(WHITE_SEAT)) {
            seatImage.setImage(new Image(GREEN_SEAT));
            if (!choosedList.contains(seatId)) {
                choosedList.add(seatId);
            }
        } else if (seatImage.getImage().getUrl().endsWith(GREEN_SEAT)) {
            seatImage.setImage(new Image(WHITE_SEAT));
            choosedList.remove(Integer.valueOf(seatId));
        }
    }

    private void setupConfirmButton() {
        confirmButton.setOnAction(event -> handleConfirmButtonClick());
    }

    @FXML
    private void handleConfirmButtonClick() {
        // Handle confirm button click (e.g., save the reservation)
        System.out.println("Confirm button clicked. Selected seats: " + choosedList);
    }

    @FXML
    private void seat1Clicked() { handleSeatClick(1); }
    @FXML
    private void seat2Clicked() { handleSeatClick(2); }
    @FXML
    private void seat3Clicked() { handleSeatClick(3); }
    @FXML
    private void seat4Clicked() { handleSeatClick(4); }
    @FXML
    private void seat5Clicked() { handleSeatClick(5); }
    @FXML
    private void seat6Clicked() { handleSeatClick(6); }
    @FXML
    private void seat7Clicked() { handleSeatClick(7); }
    @FXML
    private void seat8Clicked() { handleSeatClick(8); }
    @FXML
    private void seat9Clicked() { handleSeatClick(9); }
    @FXML
    private void seat10Clicked() { handleSeatClick(10); }
    @FXML
    private void seat11Clicked() { handleSeatClick(11); }
    @FXML
    private void seat12Clicked() { handleSeatClick(12); }
    @FXML
    private void seat13Clicked() { handleSeatClick(13); }
    @FXML
    private void seat14Clicked() { handleSeatClick(14); }
    @FXML
    private void seat15Clicked() { handleSeatClick(15); }
    @FXML
    private void seat16Clicked() { handleSeatClick(16); }
    @FXML
    private void seat17Clicked() { handleSeatClick(17); }
    @FXML
    private void seat18Clicked() { handleSeatClick(18); }
    @FXML
    private void seat19Clicked() { handleSeatClick(19); }
    @FXML
    private void seat20Clicked() { handleSeatClick(20); }
    @FXML
    private void seat21Clicked() { handleSeatClick(21); }
    @FXML
    private void seat22Clicked() { handleSeatClick(22); }
    @FXML
    private void seat23Clicked() { handleSeatClick(23); }
    @FXML
    private void seat24Clicked() { handleSeatClick(24); }
    @FXML
    private void seat25Clicked() { handleSeatClick(25); }
    @FXML
    private void seat26Clicked() { handleSeatClick(26); }
    @FXML
    private void seat27Clicked() { handleSeatClick(27); }
    @FXML
    private void seat28Clicked() { handleSeatClick(28); }
    @FXML
    private void seat29Clicked() { handleSeatClick(29); }
    @FXML
    private void seat30Clicked() { handleSeatClick(30); }
    @FXML
    private void seat31Clicked() { handleSeatClick(31); }
    @FXML
    private void seat32Clicked() { handleSeatClick(32); }

    private void handleSeatClick(int seatId) {
        ImageView seatImage = (ImageView) seatGrid.lookup("#seat" + seatId);
        if (seatImage != null) {
            handleSeatClick(seatImage, seatId);
        }

    }
}   

