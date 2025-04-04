/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Oscar
 */
public class Conexion {
    // Datos de conexión a la BD
    public static final String USUARIO = "root";
    public static final String CLAVE = "";
    public static final String database = "prueba";
    public static final String URL = "jdbc:mysql://localhost:3306/"+database;

    /**
     * Método para establecer y retornar una nueva conexión a la base de datos.
     * 
     * @return Objeto Connection si la conexión es exitosa.
     */
    public static Connection Conectar() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("✅ Conexión exitosa a la base de datos.");
            return conexion;
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: No se encontró el driver JDBC.");
            e.printStackTrace();
            throw new RuntimeException("Error al cargar el driver JDBC.", e);
        } catch (SQLException e) {
            System.err.println("❌ Error en la conexión a la base de datos: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error en la conexión a la base de datos.", e);
        }
    }

    /**
     * Método para cerrar la conexión a la base de datos.
     * 
     * @param conexion Objeto Connection que debe ser cerrado.
     */
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("🔒 Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}