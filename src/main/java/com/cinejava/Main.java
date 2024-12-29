package com.cinejava;

import javafx.fxml.FXMLLoader;

import java.net.URL;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Scene scene;
    private static final String fxmlPath = "views/";

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getClassLoader().getResource(fxmlPath.concat("LoginPage.fxml"));

        FXMLLoader loader = new FXMLLoader(url);
        Parent mainCallWindowFXML = loader.load();
<<<<<<< Updated upstream

        scene = new Scene (mainCallWindowFXML, 1300, 700);
=======
        
        Image icon = new Image("main/resources/images/icon.png");
        primaryStage.getIcons().add(icon);
        scene = new Scene (mainCallWindowFXML, 1300, 700);
        scene.getStylesheets().add(getClass().getResource("main/resources/css/styles.css").toExternalForm());
>>>>>>> Stashed changes
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("CineJava");
        primaryStage.show(); 
    }

    public static void setRoot(String fxmlFile) throws Exception {
        try{
            URL url = Main.class.getClassLoader().getResource(fxmlPath.concat(fxmlFile));
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            
            scene.setRoot(root, 1300, 700, bgColor="#213555");
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
