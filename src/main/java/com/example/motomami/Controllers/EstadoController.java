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

public class EstadoController implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextArea idDescripcion;

    @FXML
    private Button btnAtras;

    Auxiliar a = new Auxiliar();
    DB db = new DB();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void irPaginaMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/PaginaUserNormal.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Menú Principal");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btnAtras.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void buscarEstado() throws SQLException {
        if (a.comprobarCampoVacio(id.getText())){
           String resultado = db.buscarEstado(Integer.parseInt(id.getText()));
           idDescripcion.setText(resultado);
        }
    }

}
