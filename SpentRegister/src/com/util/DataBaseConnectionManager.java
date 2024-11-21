/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author C2A601-08
 */
public class DataBaseConnectionManager {
    
    // Variables de conexión
        private static String url = "jdbc:mysql://localhost:3310/gastos_personales";
        private static String usuario = "root"; // Tu usuario de MySQL
        private static String contrasena = "root"; // Tu contraseña de MySQL

        // Conexión
        private static Connection conexion = null;
    
    public static Connection getConnection() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = (Connection) DriverManager.getConnection(url, usuario, contrasena);
        }
        return conexion;
    }
    
    public void closeConnection() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
}
