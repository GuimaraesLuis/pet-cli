package BancoDados;

import java.sql.*;

public class ConectionBase {
    private static Connection connection;

    public static boolean connect(){
        try {
            String url = "jdbc:mysql://localhost/pet-cli";
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Conectou");

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean close(){
        try {
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static Connection getConnection() {
        return connection;
    }

    public Statement AddStatement(){
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            return null;
        }
    }

    public PreparedStatement CriarPreparedStatement(String sql){
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            return null;
        }
    }
}
