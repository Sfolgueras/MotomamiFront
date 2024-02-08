package com.example.motomami.Controllers;

import com.example.motomami.Utils.Auxiliar;
import com.example.motomami.Utils.DB;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InicioSesionController implements Initializable {
    @FXML
    private TextField idCorreo;
    @FXML
    private PasswordField idContra;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnRegistro;
    @FXML
    private Button btnPrecios;
    String correo;
    String contra;

    Auxiliar a = new Auxiliar();

    DB db = new DB();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    protected void iniciarSesion() throws IOException, SQLException {
        correo = idCorreo.getText();
        contra = idContra.getText();
        boolean contraLlena = a.comprobarCampoVacio(contra);
        Connection con = db.createConnectionDB();
        boolean inicioSesionExitoso = db.comprobarLogin(con, correo, contra);
        if (contraLlena) {
            boolean emailValido = a.comprobarEmail(correo);
            if (emailValido) {
                if (a.espaciosEnContrasena(contra)) {
                    if (inicioSesionExitoso) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/PaginaUserNormal.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage helloStage = new Stage();
                        helloStage.setTitle("Menú Principal");
                        helloStage.setScene(scene);
                        helloStage.show();
                        Stage stage = (Stage) btnInicio.getScene().getWindow();
                        stage.close();
                        db.closeConnectionBD(con);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Inicio sesion fallido");
                        alert.setContentText("La usuaria o la contraseña son incorrectos");
                        alert.showAndWait();
                        db.closeConnectionBD(con);
                    }
                }
            }
        }
    }
    @FXML
    public void cambioContraseña() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/CambioContra.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Cambio Contraseña");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btnInicio.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void irPantallaRegistro() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/Registro.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Registro");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btnRegistro.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void irPaginaPrecios() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/Precios.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Precios");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btnPrecios.getScene().getWindow();
        stage.close();
    }
}