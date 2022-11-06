package CarSystem;

import Account.UserLoginManager;

import java.util.ArrayList;
import java.util.Scanner;

public class CarSystem {

    private static ArrayList<Category> categories;

    static {
        categories = new ArrayList<>();
        categories.add(new Category("SUV"));
        categories.add(new Category("Coupe"));
        categories.add(new Category("Roadster"));
    }


    public static void main(String[] args) {
        CarManager carManager = new CarManager();
        Scanner scanner = new Scanner(System.in);
        UserLoginManager userLoginManager = new UserLoginManager();
        do {
            String format = "│ %-45s │\n";
            System.out.println("┌───────────────────────────────────────────────┐");
            System.out.println("│                     HOME PAGE                 │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.printf(format, "▶[1]. Guest Page.");
            System.out.printf(format, "▶[2]. Login.");
            System.out.printf(format, "▶[3]. Register account.");
            System.out.printf(format, "▶[0]. Exit.");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.println("▶ Enter your choice:");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 && choice > 5) {
                    throw new RuntimeException();
                }
                switch (choice) {
                    case 1:
                        menuGuest(scanner, carManager);
                        break;
                    case 2:
                        userLoginManager.loginAccount(scanner);
                        break;
                    case 3:
                        userLoginManager.registerAccount(scanner);
                        break;
                    case 0:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Please re-enter the selection!");
            }
        } while (true);
    }


    public void menuAdmin(Scanner scanner, CarManager carManager) {
        int choice3;
        do {
            String format = "│ %-45s │\n";
            System.out.println("┌-----------------------------------------------┐");
            System.out.println("│                   MENU ADMIN                  │");
            System.out.println("├-----------------------------------------------┤");
            System.out.printf(format, "▶[1]. View Storage.");
            System.out.printf(format, "▶[2]. Add car.");
            System.out.printf(format, "▶[3]. Delete car by id.");
            System.out.printf(format, "▶[4]. Update car by id.");
            System.out.printf(format, "▶[5]. Display all course by search name.");
            System.out.printf(format, "▶[0]. Return.");
            System.out.println("└-----------------------------------------------┘");
            System.out.println("▶ Enter your choice:");
            choice3 = Integer.parseInt(scanner.nextLine());
            if (choice3 == 0) {
                break;
            }
            switch (choice3) {
                case 1:
                    carManager.display();
                    break;
                case 2:
                    carManager.add(categories, scanner);
                    break;
                case 3:
                    carManager.delete(scanner);
                    break;
                case 4:
                    carManager.update(categories, scanner);
                    break;
                case 5:
                    carManager.displayCourseByNameContaining(scanner);
                    break;
            }
        } while (true);
    }

    public static void menuGuest(Scanner scanner, CarManager carManager) {
        int choice2;
        do {
            String format = "│ %-45s │\n";
            System.out.println("┌-----------------------------------------------┐");
            System.out.println("│                   MENU GUEST                  │");
            System.out.println("├-----------------------------------------------┤");
            System.out.printf(format, "▶[1]. View cars.");
            System.out.printf(format, "▶[0]. Return.");
            System.out.println("└-----------------------------------------------┘");
            System.out.println("▶ Enter your choice:");
            choice2 = Integer.parseInt(scanner.nextLine());
            if (choice2 == 0) {
                break;
            } else {
                switch (choice2) {
                    case 1:
                        carManager.display();
                        break;
                }
            }
        } while (true);
    }

    public void menuUser(Scanner scanner, CarManager carManager) {
        int choice1;
        do {
            String format = "│ %-45s │\n";
            System.out.println("┌-----------------------------------------------┐");
            System.out.println("│                   MENU USER                   │");
            System.out.println("├-----------------------------------------------┤");
            System.out.printf(format, "▶[1]. Buy car.");
            System.out.printf(format, "▶[2]. View cart.");
            System.out.printf(format, "▶[3]. View bill.");
            System.out.printf(format, "▶[0]. Back.");
            System.out.println("└-----------------------------------------------┘");
            System.out.println("▶ Enter your choice:");
            choice1 = Integer.parseInt(scanner.nextLine());
            if (choice1 == 0) {
                break;
            } else {
                switch (choice1) {
                    case 1:
                        carManager.display();
                        carManager.addCarByUser(scanner);
                        break;
                    case 2:
                        carManager.viewCart();
                        break;
                    case 3:
                        carManager.viewBillCart();
                        break;
                }
            }
        } while (true);
    }
}