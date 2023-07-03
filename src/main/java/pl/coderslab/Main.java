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

                    System.out.println("Podaj adres e-mail: ");
                    String email = scanner.nextLine();

                    System.out.println("Podaj hasło: ");
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
                    int readUser = scanner.nextInt();
                    scanner.nextLine();

                    // wywołanie metody readUser z UserDao przekazuje ID
                    User readUserScanner = userDao.read(readUser);

                    if(readUserScanner != null) {
                    // Użytkownik został znaleziony
                    System.out.println("Znaleziono użytkownika:");
                    System.out.println("ID: " + user.getId());
                    System.out.println("Nazwa użytkownika: " + user.getUserName());
                    System.out.println("Adres e-mail: " + user.getEmail());
                    System.out.println("Hasło: " + user.getPassword());
                    } else {
                        System.out.println("Użytkownik o podanym ID nie został znaleziony.");
                    }
                    break;
                case 3:
                    System.out.println("Wprowadź ID użytkownika, którego dane chcesz zaktualizować: ");

                    break;
                case 4:
                    // Usuwanie użytkownika
                    // ...
                    break;
                case 5:
                    // Wyświetlanie wszystkich użytkowników
                    User[] users = userDao.findAll();
                    // Wyświetl użytkowników
                    // ...
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