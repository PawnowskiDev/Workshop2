package pl.coderslab.entity;


import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.sql.*;
import java.util.Arrays;
import java.util.ResourceBundle;

public class UserDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    // metoda do tworzernia użytkownika w bazie danych

    public void create(User user) {

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

    public User read(int userId) {
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
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user) {
        try(Connection connection = DbUtil.connection();
        PreparedStatement statement = connection.prepareStatement("UPDATE workshop2.users SET email = ?, username = ?, password = ? WHERE id = ?")) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("No user found with the provided ID!");
            } else {
                System.out.println("User updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete (int userId) {
        try (Connection connection = DbUtil.connection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM workshop2.users WHERE id = ?")) {

            statement.setInt(1, userId);

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0) {
                System.out.println("No user found withe the provided ID!");
            } else {
                System.out.println("User deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User[] findAll() {

        User[] users = new User[0];

        try (Connection connection = DbUtil.connection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM workshop2.users")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = mapResultSetToUser(resultSet);
                users = addToArray(user, users);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }


    
    public String hashPassword (String password) {
        return BCrypt.hashpw (password, BCrypt.gensalt());
    }

    private User[] addToArray (User user, User[] users) {
        User[] tempUser = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1
        tempUser[users.length] = user; // Dodajemy obiekt na ostatniej pozycji
        return tempUser; // Zwracamy nową tablice
    }

    // metoda odpowiedzialna za przekształcenie pojedyńczego wiersza z wyników zapytania
    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }


}
