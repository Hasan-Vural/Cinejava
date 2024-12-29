package com.cinejava.controllers;

import com.cinejava.Main;
import com.cinejava.infrastructure.AuthSingleton;
import com.cinejava.models.User;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ProfilePageController {

    @FXML
    private Text firstandlastname;

    @FXML
    private Text usernameLabel;

    /**
     * Sayfa yüklendiğinde bilgileri ayarla.
     */
    @FXML
    public void initialize() {
        // Kullanıcı bilgilerini al ve ayarla
        User loggedUser = AuthSingleton.getInstance().getLoggedInUser(); // Örnek isim, dinamik olarak kullanıcıdan
                                                                         // alınabilir

        firstandlastname.setText(loggedUser.getFirstName().concat(" ").concat(loggedUser.getLastName()));
        usernameLabel.setText(loggedUser.getUsername());
    }

    /**
     * Ana sayfaya geri dönme işlemi.
     */
    @FXML
    private void returnhomePageclicked() {
        try {
            Main.setRoot("HomePage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
