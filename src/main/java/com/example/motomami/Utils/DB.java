package com.example.motomami.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    //Metodo para iniciar la conexion con la base de datos
    public Connection createConnectionDB() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/motomamiFront","root","Sergino_PRO1");
    }
    public void closeConnectionBD(Connection con) throws SQLException {
        con.close();
    }
}
