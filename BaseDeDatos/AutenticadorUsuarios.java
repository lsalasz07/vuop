package BaseDeDatos;

import java.sql.*;

public class AutenticadorUsuarios {

    private static final String URL = "jdbc:mysql://localhost:3306/prueba_bd2";
    private static final String USER = "root";
    private static final String PASSWORD = "prueba1";

    public boolean verificarUsuario(String correo, String clave) {
        String sql;
        boolean resultado = false;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = prepareStatement(conn, correo, clave)) {

            // Ejecutamos la consulta
            ResultSet result = statement.executeQuery();

            // Confirmamos si el resultado tiene datos
            if (result.next()) {
                resultado = true;
                System.out.println("Usuario encontrado: " + result.getString("correo"));
            } else {
                System.out.println("No se encontró ningún usuario con esas credenciales.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    private PreparedStatement prepareStatement(Connection conn, String correo, String clave) throws SQLException {
        String sql;
        PreparedStatement statement;

        // Si no se proporciona la clave, solo valida el correo
        if (clave == null || clave.isEmpty()) {
            sql = "SELECT * FROM usuario WHERE correo = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, correo);
        } else {
            // Si se proporciona la clave, valida correo y contraseña
            sql = "SELECT * FROM usuario WHERE correo = ? AND clave = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, correo);
            statement.setString(2, clave);
        }

        return statement;
    }
}
