package com.example.motomami;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Aplicacion extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Aplicacion.class.getResource("InicioSesion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 650);
        stage.setTitle("¡Bienvenida a Motomami!");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}