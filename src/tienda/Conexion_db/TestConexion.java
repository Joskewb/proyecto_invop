package tienda.Conexion_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Obtener conexión
            conn = Conexion.conectar();

            // Consulta SQL para obtener todos los datos de la tabla usuario
            String sql = "SELECT * FROM usuario";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                // Iterar sobre el conjunto de resultados e imprimir los datos
                while (rs.next()) {
                    int id = rs.getInt("idusuario");
                    String usuario = rs.getString("usuario");
                    String password = rs.getString("password");

                    System.out.println("ID: " + id + ", Nombre: " + usuario + ", Email: " + password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar conexión
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
