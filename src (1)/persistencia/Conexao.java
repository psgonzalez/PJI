package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static final String URL = "jdbc:mysql://localhost:3306/eventif?useTimezone=true&serverTimezone=UTC";
    public static final String USUARIO = "root";
    public static final String SENHA = "";

    public static Connection getConnection() {
        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            return conexao;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
