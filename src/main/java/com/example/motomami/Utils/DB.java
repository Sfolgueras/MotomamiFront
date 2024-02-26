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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/PaginaUserNormal.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage helloStage = new Stage();
                helloStage.setTitle("Menú Principal");
                helloStage.setScene(scene);
                helloStage.show();
                Stage stage = (Stage) btn.getScene().getWindow();
                stage.close();
            } else if (tipo.equals("worker")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/motomami/PaginaTrabajador.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage helloStage = new Stage();
                helloStage.setTitle("Menú Principal");
                helloStage.setScene(scene);
                helloStage.show();
                Stage stage = (Stage) btn.getScene().getWindow();
                stage.close();
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

    public boolean emailYaRegistrado(String email) throws SQLException {
        Connection con = createConnectionDB();
        String query = "SELECT * FROM mm_user WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            mostrarMensajeError("Correo ya registrado","El correo introducido ya es usado en alguna otra cuenta");
            return false;
        }
        return true;
    }


    public void insertarParte(Date fecha, String descripcion, String danios) throws SQLException {
        Connection con = createConnectionDB(); // Suponiendo que tienes un método createConnectionDB() que devuelve una conexión a la base de datos
        String query = "INSERT INTO mm_part (fecha, descripcion, daños) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(fecha.getTime())); // Convertir el objeto Date de Java a java.sql.Date
        ps.setString(2, descripcion);
        ps.setString(3, danios);

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public String buscarUsuario(String apellidos, String dni) throws SQLException {
        StringBuilder resultado = new StringBuilder();
        Connection con = createConnectionDB(); // Suponiendo que tienes un método createConnectionDB() que devuelve una conexión a la base de datos
        String query = "SELECT nombre, apellidos, fecha_nacimiento, direccion, telefono, dni FROM mm_user WHERE apellidos = ? AND dni = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, apellidos);
        ps.setString(2, dni);
        ResultSet rs = ps.executeQuery();

        // Procesar los resultados
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            String apellidosUsuario = rs.getString("apellidos");
            String fechaNacimiento = rs.getString("fecha_nacimiento");
            String direccion = rs.getString("direccion");
            int telefono = rs.getInt("telefono");
            String dniUsuario = rs.getString("dni");

            // Agregar los campos del usuario al resultado
            resultado.append("Nombre: ").append(nombre).append("\n");
            resultado.append("Apellidos: ").append(apellidosUsuario).append("\n");
            resultado.append("Fecha de nacimiento: ").append(fechaNacimiento).append("\n");
            resultado.append("Dirección: ").append(direccion).append("\n");
            resultado.append("Teléfono: ").append(telefono).append("\n");
            resultado.append("DNI: ").append(dniUsuario).append("\n");
        }
        // Cerrar recursos
        rs.close();
        ps.close();
        con.close();
        return resultado.toString();
    }
    public void generacionEstado(int id, String descripcion) throws SQLException {
        Connection con = createConnectionDB();
        String query = "INSERT INTO mm_state (id, descripcion) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, descripcion);
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public String buscarEstado(int id) throws SQLException {
        String estadoEncontrado = null;
        Connection con = createConnectionDB(); // Suponiendo que tienes un método createConnectionDB() que devuelve una conexión a la base de datos
        String query = "SELECT descripcion FROM mm_state WHERE id = ? ";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            estadoEncontrado = rs.getString("descripcion");
        }
        rs.close();
        ps.close();
        con.close();
        return estadoEncontrado;
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
