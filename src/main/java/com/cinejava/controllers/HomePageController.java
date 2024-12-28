package com.cinejava.controllers;

import com.cinejava.Main;

import javafx.fxml.FXML;

public class HomePageController {
    @FXML
    private void goToSecondPage() throws Exception {
        // Ana sahnenin root düğümünü değiştir
        Main.setRoot("SecondPage.fxml");
    }
}
