package com.example.motomami.Controllers;

import com.example.motomami.Utils.Auxiliar;
import com.example.motomami.Utils.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PartesController implements Initializable {
   @FXML
   private TextArea idDescripcion;
   @FXML
   private Button btnEntregar;
    @FXML
    private Button btnAtras;
   @FXML
   private DatePicker idFecha;
   @FXML
   private ChoiceBox<String> idDanyos;

   Auxiliar a = new Auxiliar();
   DB db = new DB();

   String texto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] danyos = {"frontal","lateral","trasero"};
        idDanyos.getItems().addAll(danyos);
        idDanyos.setValue(danyos[0]);
        idFecha.setValue(LocalDate.now());

    }
   @FXML
    public void entregarParte() throws IOException {
        texto = idDescripcion.getText();
        if (a.comprobarCampoVacio(texto)) {
            if (a.comprobarFechaAFuturo(idFecha)) {
                db.mostrarMensaje("Parte enviado","El parte se ha generado de manera existosa");
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
        }
   }
   @FXML
    public void irPaginaMenu()throws IOException{
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/PaginaUserNormal.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage helloStage = new Stage();
       helloStage.setTitle("Menú Principal");
       helloStage.setScene(scene);
       helloStage.show();
       Stage stage = (Stage) btnEntregar.getScene().getWindow();
       stage.close();
   }
}
