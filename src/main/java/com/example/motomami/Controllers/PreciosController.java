package com.example.motomami.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreciosController implements Initializable {
    @FXML
    private Button btnAtras;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void irPaginaInicio() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/InicioSesion.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Inicio Sesión");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btnAtras.getScene().getWindow();
        stage.close();
    }
}
