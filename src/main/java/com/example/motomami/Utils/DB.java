package com.example.motomami.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DB {
    Auxiliar a = new Auxiliar();
    //Metodo para iniciar la conexion con la base de datos
    public Connection createConnectionDB() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/motomamiFront","root","Sergino_PRO1");
    }
    //Metodo para cerrar la conexion con la base de datos
    public void closeConnectionBD(Connection con) throws SQLException {
        con.close();
    }

    //Metodo que comprueba que la usuaria esta registrada en la app
    public boolean comprobarLogin(Connection con, String email, String contra){
        String query = "SELECT COUNT(*) AS numCliente FROM mm_user WHERE email = ? AND contrasenia = ? ;";
        try {
            int numCliente = 0;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,email);
            ps.setString(2,contra);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                numCliente = rs.getInt("numCliente");
            }
            return numCliente > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registrarUsuario(String nombre, String apellidos, String dni, String correo, String contrasenia,String direccion, String telefono, String carnet, String matricula, String modelo, String marca, String tipoVehiculo, String sexo, String fecha_nacimiento) throws SQLException {
        String query = "INSERT INTO mm_user (nombre, apellidos, dni, email, contrasenia, direccion, telefono, " +
                "carnet_conducir, matricula, modelo, marca, tipo_vehiculo, genero, fecha_nacimiento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = createConnectionDB();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, nombre);
        ps.setString(2, apellidos);
        ps.setString(3, dni);
        ps.setString(4, correo);
        ps.setString(5, contrasenia);
        ps.setString(6, direccion);
        ps.setString(7, telefono);
        ps.setString(8, carnet);
        ps.setString(9, matricula);
        ps.setString(10, modelo);
        ps.setString(11, marca);
        ps.setString(12, tipoVehiculo);
        ps.setString(13, sexo);
        ps.setString(14,fecha_nacimiento);
        int rs = ps.executeUpdate();
        if (rs > 0){
            mostrarMensaje("Registro existoso","La usuaria se registró correctamente.");
        } else {
            mostrarMensajeError("Error", "Ocurrió un error al intentar registrar la usuaria.");
        }
    }
    public void tipoUsuario(String email, Button btn) throws SQLException, IOException {
        Connection con = createConnectionDB();
        String query = "SELECT tipo_usuario FROM mm_user WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String tipo = rs.getString("tipo_usuario");
            if (tipo.equals("customer")){
                a.irPaginaCliente(btn);
            } else if (tipo.equals("worker")){
                a.irPaginaTrabajador(btn);
            } else {
                mostrarMensajeError("Error","Error en el inicio de sesión");
            }
        } else {
            mostrarMensajeError("Error", "El usuario no existe");
        }
    }
    public void cambioContrasenya(String contrasenaNueva, String email) throws SQLException {
        Connection con = createConnectionDB();
        String query = "UPDATE mm_user SET contrasenia = ? where email = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,contrasenaNueva);
        ps.setString(2,email);
        int rs = ps.executeUpdate();
        if (rs > 0){
            mostrarMensaje("Cambio contraseña exitoso","La contraseña se ha cambiado correctamente");
        } else{
            mostrarMensajeError("Error","el usuario no existe");
        }
    }

    public void mostrarMensaje(String titulo, String contenido){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    public void mostrarMensajeError(String titulo, String contenido){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}
