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
                    // Dodawanie użytkownika
                    User user = new User();
                    // Pobierz dane użytkownika od użytkownika
                    // ...

                    userDao.create(user);
                    System.out.println("Użytkownik dodany.");
                    break;
                case 2:
                    // Wyszukiwanie użytkownika
                    // ...
                    break;
                case 3:
                    // Aktualizacja użytkownika
                    // ...
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