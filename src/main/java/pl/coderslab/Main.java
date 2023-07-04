package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Tworzenie obiektu UserDao
        UserDao userDao = new UserDao();

        // Tworzenie obiektu Scanner do odczytu danych wejściowych
        Scanner scanner = new Scanner(System.in);

        // Pętla menu
        boolean running = true;
        while (running) {
            System.out.println("1. Dodaj użytkownika");
            System.out.println("2. Wyszukaj użytkownika");
            System.out.println("3. Zaktualizuj użytkownika");
            System.out.println("4. Usuń użytkownika");
            System.out.println("5. Wyświetl wszystkich użytkowników");
            System.out.println("0. Wyjście");
            System.out.print("Wybierz opcję: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Pobranie pozostałego znaku nowej linii

            switch (option) {
                case 1:
                    System.out.print("Podaj nazwę użytkownika: ");
                    String userName = scanner.nextLine();

                    System.out.print("Podaj adres e-mail: ");
                    String email = scanner.nextLine();

                    System.out.print("Podaj hasło: ");
                    String password = scanner.nextLine();

                    // Tworzenie obiektu User z pobranych danych
                    User user = new User();
                    user.setUserName(userName);
                    user.setEmail(email);
                    user.setPassword(password);

                    // Dodawanie użytkownika do bazy danych
                    userDao.create(user);
                    System.out.println("Użytkownik dodany.");
                    break;
                case 2:
                    System.out.println("Wprowadź ID użytkownika, którego chcesz wyszukać: ");
                    int readUserId = scanner.nextInt();
                    scanner.nextLine();

                    // wywołanie metody readUser z UserDao przekazuje ID
                    User readUserScanner = userDao.read(readUserId);

                    if(readUserScanner != null) {
                    // Użytkownik został znaleziony
                    System.out.println("Znaleziono użytkownika:");
                    System.out.println("ID: " + readUserScanner.getId());
                    System.out.println("Nazwa użytkownika: " + readUserScanner.getUserName());
                    System.out.println("Adres e-mail: " + readUserScanner.getEmail());
                    System.out.println("Hasło: " + readUserScanner.getPassword());
                    } else {
                        System.out.println("Użytkownik o podanym ID nie został znaleziony.");
                    }
                    break;
                case 3:
                    System.out.println("Wprowadź ID użytkownika, którego dane chcesz zaktualizować: ");
                    int updateUserId = scanner.nextInt();
                    scanner.nextLine();

                    // wyszukanie użytkownika po podanym ID
                    User updateUser = userDao.read(updateUserId);

                    if (updateUser != null) {
                        System.out.println("Znaleziono użytkownika, wprowadź nowe dane: ");

                        // Aktualizacja poszczególnych pól użytkownika
                        System.out.println("Wprowadź nową nazwę użytkownika: ");
                        String newUsername = scanner.nextLine();
                        updateUser.setUserName(newUsername);

                        System.out.println("Wprowadź nowy adres e-mail: ");
                        String newEmail = scanner.nextLine();
                        updateUser.setEmail(newEmail);

                        System.out.println("Wprowadź nowe hasło: ");
                        String newPassword = scanner.nextLine();
                        updateUser.setPassword(newPassword);

                        // wywołanie metody update z UserDao
                        userDao.update(updateUser);

                        System.out.println("Dane zostały zaktualizowane.");
                    } else {
                        System.out.println("Uzytkownik o podanym ID nie został znaleziony");
                    }
                    break;
                case 4:
                    System.out.println("Wprowdź ID użytkownika, którego chcesz usunąć: ");
                    int deleteUserID = scanner.nextInt();
                    scanner.nextLine();

                    // wywołanie metody delete z UserDao
                    User deleteUser = userDao.delete(deleteUserID);

                    if (deleteUser == null) {
                        System.out.println("Użytkownik o podanym ID nie został znaleziony!");
                    } else {
                        System.out.println("Użytkownik został pomyślnie usunięty.");
                    }
                    break;
                case 5:
                    // wywołanie metody findAll z UserDao
                    User[] users = userDao.findAll();

                    if (users.length == 0) {
                        System.out.println("Brak użytkowników do wyświetlenia!");
                    } else {
                        System.out.println("Lista użytkowników: ");

                        for (User allUser : users) {
                            System.out.println("ID: " + allUser.getId());
                            System.out.println("Nazwa użytkownika: " + allUser.getUserName());
                            System.out.println("Adres e-mail: " + allUser.getEmail());
                            System.out.println("Hasło: " + allUser.getPassword());
                            System.out.println("---------------------------");
                        }
                    }
                    break;
                case 0:
                    // Wyjście z programu
                    running = false;
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
            }
        }
    }
}