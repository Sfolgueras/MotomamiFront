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

public class PaginaUserNormalController implements Initializable {
    @FXML
    private Button btnPartes;
    @FXML
    private Button btnEstado;
    @FXML
    private Button btnFacturas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void irPantallaPartes() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/Partes.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Partes");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btnPartes.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void irPantallaEstado() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/Estado.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Partes");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btnEstado.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void irPantallaFactura() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/Facturas.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Partes");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btnFacturas.getScene().getWindow();
        stage.close();
    }
}
