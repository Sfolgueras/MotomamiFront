package com.example.motomami.Utils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class Auxiliar {

    public Auxiliar(){

    }
    public boolean comprobarCampoVacio(String campo) {
        if (campo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hay campos vacios");
            alert.setContentText("No pueden haber campos vacios");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    public boolean espaciosEnContrasena(String campo){
        if (campo.contains(" ")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Espacio en la contraseña");
            alert.setContentText("No pueden haber espacios en la contraseña");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public boolean comprobarEmail(String campo){
        String expresionEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!campo.matches(expresionEmail)){
            Alert alertEmail = new Alert(Alert.AlertType.ERROR);
            alertEmail.setTitle("Error en el email");
            alertEmail.setContentText("Formato incorrecto en el email (ejemplo@gmail.com)");
            alertEmail.showAndWait();
            return false;
        }
        return true;
    }
    public boolean comprobarFechaAFuturo(DatePicker datePicker){
        LocalDate date = datePicker.getValue();
        if (date.isAfter(LocalDate.now())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fecha incorrecta");
            alert.setContentText("No puedes poner una fecha a futuro ");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    public boolean comprobarNumeros(String campo){
        for(char c : campo.toCharArray()){
            if (Character.isDigit(c)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Numeros en campos invalidos");
                alert.setContentText("No puedes poner numeros en el campo de nombre/apellidos" );
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }
    public Boolean validarDNI(String s) {
        if (s.length() != 9) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Longitud incorrecta");
            alert.setContentText("El DNI debe tener 9 caracteres.");
            alert.showAndWait();
            return false;
        }
        int numDNI = Integer.parseInt(s.substring(0, 8));
        String letra = s.substring(8, 9);
        String[] letrasDNI = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        int posicio = numDNI % 23;
        if (!letra.equalsIgnoreCase((letrasDNI[posicio]))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DNI incorrecto");
            alert.setContentText("El dni no existe");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void irPaginaCliente(Button btn) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/PaginaUserNormal.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Menú Principal");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
    public void irPaginaTrabajador(Button btn) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/PaginaTrabajador.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage helloStage = new Stage();
        helloStage.setTitle("Menú Principal");
        helloStage.setScene(scene);
        helloStage.show();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
