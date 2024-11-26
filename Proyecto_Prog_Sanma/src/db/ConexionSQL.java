package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
    public static void main(String[] args) {
        // Ruta de la base de datos
        String url = "jdbc:sqlite:resources/db/sanMames.db";

        // Asegurar que la carpeta resources/db existe
        new java.io.File("resources/db").mkdirs();

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Base de datos creada o abierta con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}
