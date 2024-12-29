// package com.example.demo1;

// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.PasswordField;
// import javafx.scene.control.TextField;
// import javafx.stage.Stage;

// public class SignUpController {

//     @FXML
//     private TextField usernameField;
//     @FXML
//     private PasswordField passwordField;
//     @FXML
//     private PasswordField confirmPasswordField;
//     @FXML
//     private Button signUpButton;

//     @FXML
//     protected void onSignUpButtonClick() {
//         String username = usernameField.getText();
//         String password = passwordField.getText();
//         String confirmPassword = confirmPasswordField.getText();

//         if (password.equals(confirmPassword)) {
//             // Kayıt başarılı
//             User new_user = new User(username, password);
//             Main.manager.add_user(new_user);
//             Alert alert = new Alert(Alert.AlertType.INFORMATION);
//             alert.setTitle("Kayıt Başarılı");
//             alert.setContentText("Kayıt başarılı! Artık giriş yapabilirsiniz.");
//             alert.showAndWait();
//         } else {
//             // Şifreler eşleşmiyor
//             Alert alert = new Alert(Alert.AlertType.ERROR);
//             alert.setTitle("Hata");
//             alert.setContentText("Şifreler eşleşmiyor!");
//             alert.showAndWait();
//         }
//     }

//     @FXML
//     protected void onBackToLoginButtonClick() {
//         try {
//             // Giriş ekranına geri dön
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginEkrani.fxml"));
//             Parent root = loader.load();
//             Stage stage = (Stage) signUpButton.getScene().getWindow();
//             stage.setScene(new Scene(root));
//             stage.show();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }
