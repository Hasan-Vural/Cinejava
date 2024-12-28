package com.cinejava.controllers;

import javafx.fxml.FXML;
import com.cinejava.Main;

public class SecondPageController {
    @FXML
    private void goToHomePage() throws Exception {
        // Ana sahnenin root düğümünü değiştir
        Main.setRoot("Homepage.fxml");
    }
}
