package com.example.motomami.Controllers;

import com.example.motomami.Utils.Auxiliar;
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
import java.sql.*;

public class RegistroController implements Initializable {
    @FXML
    private DatePicker idFechaNacimiento;
    @FXML
    private TextField idNombre;
    @FXML
    private TextField idApellidos;
    @FXML
    private TextField idDNI;
    @FXML
    private TextField idCorreo;
    @FXML
    private PasswordField idContra;
    @FXML
    private TextField idDireccion;
    @FXML
    private TextField idTelefono;
    @FXML
    private TextField idCarnet;
    @FXML
    private TextField idMatricula;
    @FXML
    private TextField idModelo;
    @FXML
    private TextField idMarca;
    @FXML
    private Button btnEntregar;
    @FXML
    private Button btnAtras;
    @FXML
    private ChoiceBox<String> TipoVehiculo;
    @FXML
    private ChoiceBox<String> Sexo;
    Auxiliar a = new Auxiliar();
    String nombre;
    String apellidos;
    String DNI;
    String correo;
    String contra;

    Alert alert;

    private TextInputControl[] campos;
    String[] valores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idFechaNacimiento.setValue(LocalDate.now());
        String[] vehiculos = {"patinete","bici","moto","coche","furgoneta"};
        String[] sexos = {"mujer", "hombre"};
        TipoVehiculo.getItems().addAll(vehiculos);
        TipoVehiculo.setValue(vehiculos[0]);
        Sexo.getItems().addAll(sexos);
        Sexo.setValue(sexos[0]);
        campos = new TextInputControl[]{idNombre, idApellidos, idDNI, idCorreo, idDireccion, idTelefono, idMarca, idMatricula, idModelo, idCarnet};
        valores = new String[campos.length];
    }

        @FXML
        public void registrarse() throws IOException {
            nombre = idNombre.getText();
            apellidos = idApellidos.getText();
            DNI = idDNI.getText();
            contra = idContra.getText();
            correo = idCorreo.getText();
            for (int i = 0; i < campos.length; i++) {
                valores[i] = campos[i].getText();
                if(!a.comprobarCampoVacio(valores[i]) && !a.comprobarCampoVacio(contra)){
                    return;
                }
            }
            if (Sexo.getValue().equals("hombre")){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("MALDITO HOMBRE");
                alert.setContentText("NO PUEDES ENTRAR EN ESTA APLICACIÓN MACHITO");
                alert.showAndWait();
                return;
            }
            LocalDate fechaNacimiento = idFechaNacimiento.getValue();
            int anyoNacimiento = fechaNacimiento.getYear();
            int anyoActual = LocalDate.now().getYear();
            if(anyoActual - anyoNacimiento < 16){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Edad insuficiente");
                alert.setContentText("No tienes la edad suficiente para tener ningún permiso de conducción");
                alert.showAndWait();
                return;
            }
            if (anyoActual - anyoNacimiento < 18 && TipoVehiculo.getValue().equals("moto") || anyoActual - anyoNacimiento < 18 && TipoVehiculo.getValue().equals("coche") ||anyoActual - anyoNacimiento < 18 && TipoVehiculo.getValue().equals("furgoneta")){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Edad insuficiente");
                alert.setContentText("No tienes la edad suficiente para este tipo de vehiculo");
                alert.showAndWait();
                return;
            }

            if (a.comprobarEmail(correo) && a.validarDNI(DNI)){
                if (a.espaciosEnContrasena(contra) && a.comprobarNumeros(nombre) && a.comprobarNumeros(apellidos)) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registro Exitoso");
                    alert.setContentText("Registrado de manera exitosa");
                    alert.showAndWait();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/InicioSesion.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage helloStage = new Stage();
                    helloStage.setTitle("Cambio Contraseña");
                    helloStage.setScene(scene);
                    helloStage.show();
                    Stage stage = (Stage) btnEntregar.getScene().getWindow();
                    stage.close();
                }
            }
        }
        @FXML
        public void irInicioSesion() throws IOException{
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
