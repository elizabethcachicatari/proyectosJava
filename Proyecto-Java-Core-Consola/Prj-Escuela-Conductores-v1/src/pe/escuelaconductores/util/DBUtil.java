package pe.escuelaconductores.util;

import pe.escuelaconductores.clases.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static String url="jdbc:oracle:thin:@localhost:1521:xe";
    private static String usuario;
    private static String contrasena;

    public static Connection conectar(User user) throws SQLException {
        usuario =user.getUser();
        contrasena =user.getPassword();
        return DriverManager.getConnection(url,usuario, contrasena);
    }
}
