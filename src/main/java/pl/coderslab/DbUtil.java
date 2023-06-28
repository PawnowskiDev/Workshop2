package pl.coderslab;

import java.sql.*;

public class DbUtil {

    static String DB_URL = "jdbc:mysql://127.0.0.1:3306/workshop2";
    static String DB_USER = "root";
    static String DB_PASS = "!Molly2022";

    static String sql = "INSERT INTO workshop2.users (email, username, password) VALUES (?, ?, ?)";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static void insert(Connection conn, String query, String... params) throws SQLException {

        try ( PreparedStatement statement =
            conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
                for (int i = 0; i < params.length; i++) {
                    statement.setString(i + 1, params[i]);
                }

            // miejsce na kolejne metody 

            int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        int i;
                        int id = resultSet.getInt(i);
                    }
                }
                
                
                
                
            } catch (SQLException e) {
                e.printStackTrace();
                

        }
    }
}
