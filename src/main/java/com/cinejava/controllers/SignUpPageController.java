package com.cinejava.controllers;

import com.cinejava.Main;
import com.cinejava.services.implementations.UsersService;
import com.cinejava.services.interfaces.IUsersService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;

public class SignUpPageController {
    @FXML
    private StackPane pane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    private final IUsersService usersService;

    public SignUpPageController() {
        this.usersService = new UsersService();
    }

    @FXML
    public void initialize() {
        String imagePath = getClass().getResource("/images/logo.jpg").toExternalForm();

        BackgroundImage backgroundImage = new BackgroundImage(
            new javafx.scene.image.Image(imagePath),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
        );

        pane.setBackground(new Background(backgroundImage));
    }

    @FXML
    protected void onSignUpButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            alertForPasswordMismatch();
        }

        else{
            String message = usersService.register(username, password);

            if (message.equals("Kayit basarili!")) {
                alertForSignUpSuccess();
                try {
                    Main.setRoot("HomePage.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }              
            }

            else{
                alertForSignUpFailure(message);
            }
        }
    }

    @FXML
    protected void onBackToLoginButtonClick() {
        try {
            Main.setRoot("LoginPage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void alertForPasswordMismatch(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hatalı Giriş");
        alert.setContentText("Girdiğiniz şifreler aynı olmalıdır!");
        alert.showAndWait();
    }

    private void alertForSignUpFailure(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hatalı Giriş");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void alertForSignUpSuccess(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Kayıt Başarılı");
        alert.setContentText("Başarı ile kayıt oldunuz!");
        alert.showAndWait();
    }
}
