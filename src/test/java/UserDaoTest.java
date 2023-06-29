import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;


public class UserDaoTest {

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

}
