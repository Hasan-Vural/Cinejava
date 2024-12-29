package com.cinejava.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cinejava.Main;
import com.cinejava.models.Reservation;
import com.cinejava.services.implementations.ReservationsService;
import com.cinejava.singletons.MovieInstanceSingleton;
import com.cinejava.singletons.ReservationInstanceSingleton;
import com.jfoenix.controls.JFXButton;

public class BookingPageController {

    @FXML
    private Pane seatPane;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton completeReservationButton;

    // Seçili koltukları takip etmek için bir Set
    private final Set<Integer> selectedSeats = new HashSet<>();

    @FXML
    public void initialize() {
        int rows = 5; // Koltuk sırası sayısı
        int cols = 8; // Sütun sayısı
        int seatSize = 100; // Koltuk genişliği ve yüksekliği
        int spacing = 10; // Koltuklar arası boşluk
        int startX = 50; // Başlangıç X konumu
        int startY = 50; // Başlangıç Y konumu

        int seatId = 1;

        List<Integer> reservedSeats = ReservationInstanceSingleton.getInstance().getReservation().getReservedSeats();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Pane seat = createSeat(seatId, startX + col * (seatSize + spacing), startY + row * (seatSize + spacing),
                        reservedSeats.contains(seatId));
                seatPane.getChildren().add(seat);
                seatId++;
            }
        }
    }

    private Pane createSeat(int seatId, int x, int y, boolean isReserved) {
        Pane seat = new Pane();
        seat.setLayoutX(x);
        seat.setLayoutY(y);
        seat.setPrefSize(100, 100);

        ImageView seatImage = new ImageView();
        seatImage.setFitWidth(100);
        seatImage.setFitHeight(100);

        // Şablon olarak rezerve edilmiş koltuklar kırmızı
        if (isReserved) {
            seatImage.setImage(new Image(getClass().getResource("/images/redseat.png").toExternalForm()));
            seat.setOnMouseClicked(event -> System.out.println("Bu koltuk rezerve edilmiş ve seçilemez."));
        } else {
            // Beyaz koltuklar tıklanabilir
            seatImage.setImage(new Image(getClass().getResource("/images/whiteseat.png").toExternalForm()));

            seat.setOnMouseClicked(event -> {
                if (selectedSeats.contains(seatId)) {
                    // Seçiliyse beyaza dön ve işareti kaldır
                    seatImage.setImage(new Image(getClass().getResource("/images/whiteseat.png").toExternalForm()));
                    selectedSeats.remove(seatId);
                    System.out.println("Koltuk seçimi kaldırıldı: " + seatId);
                } else {
                    // Beyazdan yeşile dön ve işaretle
                    seatImage.setImage(new Image(getClass().getResource("/images/greenseat.png").toExternalForm()));
                    selectedSeats.add(seatId);
                    System.out.println("Koltuk seçildi: " + seatId);
                }
            });
        }

        seat.getChildren().add(seatImage);
        return seat;
    }

    @FXML
    private void handleBackButtonClick() {
        try {
            Main.setRoot("MoviePage.fxml");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    private void handleCompleteReservation() {
        double moviePrice = MovieInstanceSingleton.getInstance().getMovie().getTicketPrice();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Rezervasyon Onayı");
        alert.setHeaderText("Rezervasyonu Tamamlamak Üzeresiniz");
        alert.setContentText("Rezervasyonu tamamlamak istediğinize emin misiniz?\nToplam ücret:"
                + selectedSeats.size() * moviePrice);

        // Kullanıcı onayı
        if (alert.showAndWait().get().getButtonData().isDefaultButton()) {
            try {
                ReservationsService reservationsService = new ReservationsService();
                Reservation reservation = ReservationInstanceSingleton.getInstance().getReservation();
                reservationsService.insert(new Reservation(reservation.getMovieId(), reservation.getUserId(),
                        reservation.getSessionId(), new ArrayList<>(selectedSeats)));

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("İşlem Başarılı");
                alert2.setHeaderText("Rezervasyonunuz başarıyla tamamlandı!");
                alert2.showAndWait();
                Main.setRoot("HomePage.fxml"); // HomePage.fxml'e dön
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
    }
}
