package com.cinejava;

import javafx.fxml.FXMLLoader;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Scene scene;
    private static final String fxmlPath = "views/";

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getClassLoader().getResource(fxmlPath.concat("HomePage.fxml"));

        FXMLLoader loader = new FXMLLoader(url);
        Parent mainCallWindowFXML = loader.load();

        scene = new Scene (mainCallWindowFXML, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    public static void setRoot(String fxmlFile) throws Exception {
        try{
            URL url = Main.class.getClassLoader().getResource(fxmlPath.concat(fxmlFile));
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
    
            scene.setRoot(root);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
