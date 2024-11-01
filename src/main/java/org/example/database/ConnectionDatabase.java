package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    //Conexion de la base de datos con el local, usuario y contraseña.
    private static final String URL = "jdbc:mysql://localhost:3306/tienda_online";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
        //Aqui indico
    }

}
