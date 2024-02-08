package com.example.motomami.Utils;

import javafx.scene.control.Alert;

import java.sql.*;

public class DB {
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
