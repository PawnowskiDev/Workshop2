package pl.coderslab.entity;


import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.sql.*;
import java.util.ResourceBundle;

public class UserDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    // metoda do tworzernia użytkownika w bazie danych

    public void createUser (User user) {

        try (Connection connection = DbUtil.connection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    user.setId(id);
                    System.out.println("Inserted ID: " + id);
                }
            } else {
                System.out.println("Insert operation failed");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User read (int userId) {
        try (Connection connection = DbUtil.connection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM workshop2.users WHERE id = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                return user;
            } else {
                return null; // brak użytkownika o padynym id
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String hashPassword (String password) {
        return BCrypt.hashpw (password, BCrypt.gensalt());
    }


}
