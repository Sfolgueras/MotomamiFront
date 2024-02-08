package com.example.motomami.Controllers;

import com.example.motomami.Utils.Auxiliar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CambioContraController implements Initializable {
    @FXML
    private TextField idCorreo;
    @FXML
    private PasswordField idContra;
    @FXML
    private PasswordField idRepetida;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnAtras;

    String correo;
    String contra;
    String repetida;

    Auxiliar a = new Auxiliar();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void cambiarContraseña() throws IOException {
        correo = idCorreo.getText();
        contra = idContra.getText();
        repetida = idRepetida.getText();
        boolean comprobacionCorreo = a.comprobarEmail(correo);
        boolean comprobacionContra = a.comprobarCampoVacio(contra);
        Alert alert;
        if (comprobacionContra && comprobacionCorreo) {
            if (contra.equals(repetida)) {
                if (a.espaciosEnContrasena(contra)) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cambio contraseña");
                    alert.setHeaderText("¡Bien!");
                    alert.setContentText("Cambio contraseña exitoso");
                    alert.showAndWait();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/InicioSesion.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage helloStage = new Stage();
                    helloStage.setTitle("Inicio Sesión");
                    helloStage.setScene(scene);
                    helloStage.show();
                    Stage stage = (Stage) btnAceptar.getScene().getWindow();
                    stage.close();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error cambiando contraseña");
                alert.setContentText("Las contraseñas no coinciden");
                alert.showAndWait();
            }
        }
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
