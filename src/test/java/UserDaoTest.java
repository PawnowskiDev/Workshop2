import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import static org.junit.Assert.*;


public class UserDaoTest {


    @Test
    public void testCreateUser() {
        // tworzenie obiektu użytkownika do przekazania do metody createUser
        User user = new User();

        user.setUserName("example");
        user.setPassword("password123");
        user.setEmail("example@gmail.com");

        // tworzenie obiektu UserDao, który zawiera metodę createUser
        UserDao userDao = new UserDao();

        // wywołanie metody createUser
        userDao.create(user);

        // sprawdzenie, czy użytkownik został dodany do bazy

        assertNotNull(user.getId());  // sprawdzenie waruknowe które sprawdza czy user.getId() - nie jest null (asercja)
        System.out.println("User ID: " + user.getId());
    }

    @Test

    public void testReadExistingUser() {
        // tworzymy nowy obiekt UserDao
        UserDao userDao = new UserDao();

        // wywołujemy metodę read, podając istniejące id użytkownika
        User user = userDao.read(1);

        // sprawdzamy, czy metoda zwróciła obiekt a nie null
        Assertions.assertNotNull(user);

        // sprawdzamy czy obiekt ma poprawne dane
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("adam.kowalski@gmail.com", user.getEmail());
        Assertions.assertEquals("AdamKowalski", user.getUserName());

        Assertions.assertEquals(2, user.getId());
        Assertions.assertEquals("jan.nowak@gmail.com", user.getEmail());
        Assertions.assertEquals("JanNowak", user.getUserName());
    }

    @Test
    public void testReadNonExistingUser() {
        // tworzymy nowy obiek UserDao
        UserDao userDao = new UserDao();

        // wywołujemy metodę read podając nieistniejące id użytkownika
        User user = userDao.read(100);

        // sprawdzamy czy metoda zwróciła null
        Assertions.assertNull(user);
    }

    @Test
    public void testUpdateUser() {
        // tworzymy obiekt użytkownika do przekazania do metody update
        User user = new User();
        user.setId(1); // ustawiamy id istniejącego użytkownika
        user.setEmail("mail@example.com");
        user.setUserName("username");
        user.setPassword("userpassword123");

        // tworzenie obiektu UserDao który zawiera metodę update
        UserDao userDao = new UserDao();

        // wywołanie metody update
        userDao.update(user);

        // sprawdzenie za pomocą asercji
        User updateUser = userDao.read(user.getId());
        assertEquals("mail@example.com", updateUser.getEmail());
        assertEquals("username" , updateUser.getUserName());
        assertEquals("userpassword123" , updateUser.getPassword());

        System.out.println("User updated successfully");
    }

    @Test
    public void testDeleteUser() {
        // tworzenie obiektu UserDao, który zawiera metodę delete
        UserDao userDao = new UserDao();

        // tworzenie obiektu użytkownika do usunięcia
        User userToDelete = new User();
        userToDelete.setId(1); // id użytkownika do usunięcia

        // wywołanie metody delete
        userDao.delete(userToDelete.getId());

        // tak jak w poprzednich przykładach uzywamy asercji do sprawdzenia czy użytkownik nie istnieje w bazie danych
        User deletedUser = userDao.read(userToDelete.getId());
        assertNull(deletedUser);

        System.out.println("User deleted successfully!");
    }

    @Test
    public void testFindAll() {
        UserDao userDao = new UserDao();

        // Tworzenie kilku przykładowych użytkowników w bazie danych
        User user1 = new User();
        user1.setEmail("user1@example.com");
        user1.setUserName("user1");
        user1.setPassword("password1");
        userDao.create(user1);

        User user2 = new User();
        user2.setEmail("user2@example.com");
        user2.setUserName("user2");
        user2.setPassword("password2");
        userDao.create(user2);

        // Wywołanie metody findAll i otrzymanie tablicy użytkowników
        User[] users = userDao.findAll();

        // Sprawdzenie czy tablica zawiera oczekiwaną liczbę użytkowników
        assertEquals(2, users.length);

        // Sprawdzenie czy tablica zawiera utworzonych użytkowników
        assertEquals("user1@example.com", users[0].getEmail());
        assertEquals("user1", users[0].getUserName());
        assertEquals("password1", users[0].getPassword());

        assertEquals("user2@example.com", users[1].getEmail());
        assertEquals("user2", users[1].getUserName());
        assertEquals("password2", users[1].getPassword());
    }


}
