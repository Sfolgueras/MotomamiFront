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
    DB db = new DB();
    public boolean comprobarCampoVacio(String campo) {
        if (campo.isEmpty()) {
            db.mostrarMensajeError("Hay campos vacios","No pueden haber campos vacios");
            return false;
        }
        return true;
    }
    public boolean espaciosEnContrasena(String campo){
        if (campo.contains(" ")){
            db.mostrarMensajeError("Espacio en la contraseña","No pueden haber espacios en la contraseña");
            return false;
        }
        return true;
    }

    public boolean comprobarEmail(String campo){
        String expresionEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!campo.matches(expresionEmail)){
            db.mostrarMensajeError("Error en el email","Formato incorrecto en el email (ejemplo@gmail.com)");
            return false;
        }
        return true;
    }
    public boolean comprobarFechaAFuturo(DatePicker datePicker){
        LocalDate date = datePicker.getValue();
        if (date.isAfter(LocalDate.now())){
            db.mostrarMensajeError("Fecha incorrecta","No puedes poner una fecha a futuro ");
            return false;
        }
        return true;
    }
    public boolean comprobarNumeros(String campo){
        for(char c : campo.toCharArray()){
            if (Character.isDigit(c)){
                db.mostrarMensajeError("Numeros en campos invalidos","No puedes poner numeros en el campo de nombre/apellidos");
                return false;
            }
        }
        return true;
    }
    public Boolean validarDNI(String s) {
        if (s.length() != 9) {
            db.mostrarMensajeError("Longitud incorrecta","El DNI debe tener 9 caracteres.");
            return false;
        }
        int numDNI = Integer.parseInt(s.substring(0, 8));
        String letra = s.substring(8, 9);
        String[] letrasDNI = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        int posicio = numDNI % 23;
        if (!letra.equalsIgnoreCase((letrasDNI[posicio]))){
            db.mostrarMensajeError("DNI incorrecto","El dni no existe");
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
