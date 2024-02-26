package com.example.motomami.Controllers;

import com.example.motomami.Utils.Auxiliar;
import com.example.motomami.Utils.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GenerarEstadoController implements Initializable {
    @FXML
    private Button btnAtras;
    @FXML
    private TextField id;
    @FXML
    private TextArea idDescripcion;

    Auxiliar a = new Auxiliar();
    DB db = new DB();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    @FXML
    public void irPaginaAtras() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/PaginaTrabajador.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Menu principal");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btnAtras.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void generarEstado() throws SQLException {
        if (a.comprobarCampoVacio(id.getText()) && a.comprobarCampoVacio(idDescripcion.getText())){
            db.generacionEstado(Integer.parseInt(id.getText()), idDescripcion.getText());
            db.mostrarMensaje("Generado","Estado generado exitosamente");
        } else {
            db.mostrarMensajeError("Error","No se ha podido generar el estado");
        }
    }
}
