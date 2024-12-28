package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Kullanıcıları alın
        ArrayList<User> users = (ArrayList<User>) Main.manager.getUsers();

        boolean loginSuccess = false;

        // Giriş kontrolü
        for (User user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                loginSuccess = true;
                break;
            }
        }

        if (loginSuccess) {
            // Başarılı giriş alerti
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Başarılı Giriş");
            alert.setContentText("Giriş başarılı!");
            alert.showAndWait();

            // Ana ekrana geçiş
            try {
                // FXML dosyasını yükle
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
                Parent root = loader.load();

                // Yeni sahne oluştur
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Hata durumunda kullanıcıya bildirim yapabilirsiniz
            }
        } else {
            // Hatalı giriş alerti
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hatalı Giriş");
            alert.setContentText("Kullanıcı adı veya şifre hatalı!");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onSignUpButtonClick() {
        try {
            // Kayıt ekranına geçiş yapma
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUpEkrani.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}