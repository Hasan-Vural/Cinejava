package com.cinejava.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;

import com.cinejava.Main;
import com.cinejava.services.implementations.UsersService;
import com.cinejava.services.interfaces.IUsersService;

public class LoginPageController {
    @FXML
    private StackPane pane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    private final IUsersService usersService;

    public LoginPageController() {
        this.usersService = new UsersService();
    }

    @FXML
    public void initialize() {
        BackgroundFill backgroundFill = new BackgroundFill(
            javafx.scene.paint.Color.LIGHTBLUE, // Arka plan rengi
            new CornerRadii(15), // Köşe yuvarlama
            new Insets(10)       // İç kenar boşluğu
        );

        pane.setBackground(new Background(backgroundFill));
    }

    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean loginSuccess = usersService.login(username, password);

        if (loginSuccess) {
            alertForLoginSuccess();
            try {
                Main.setRoot("HomePage.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }    
        } 
        
        else {
            alertForLoginFailure();
        }
    } 
    

    @FXML
    protected void onSignUpButtonClick() {
        try {
            Main.setRoot("SignUpPage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void alertForLoginSuccess(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Başarılı Giriş");
        alert.setContentText("Giriş başarılı!");
        alert.showAndWait();
    }

    private void alertForLoginFailure(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hatalı Giriş");
        alert.setContentText("Kullanıcı adı veya şifre hatalı!");
        alert.showAndWait();
    }
}