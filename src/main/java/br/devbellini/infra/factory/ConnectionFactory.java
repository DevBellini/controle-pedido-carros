package br.devbellini.infra.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String USERNAME = "root";

    private static final String PASSWORD = "";

    private static  final String DATABASE_URL = "jdbc:mysql://localhost:3306/showtimedb";

    public static Connection createConncetionToMySQL(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection con = createConncetionToMySQL();
        if(con != null){
            System.out.println("Conexão pega com sucesso");
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
