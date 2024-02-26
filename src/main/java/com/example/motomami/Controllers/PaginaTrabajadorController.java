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

public class PaginaTrabajadorController implements Initializable {
    @FXML
    private Button btnUser;
    @FXML
    private Button btnEstado;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void irBuscarUsuario() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/BuscarUsuario.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Buscar usuario");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btnUser.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void irGenerarEstado() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/GenerarEstado.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Generar estado");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btnEstado.getScene().getWindow();
        stage.close();
    }
}
