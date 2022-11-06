package CarSystem;

import Account.User;
import IOTextFile.IOTextFile;

import java.io.*;
import java.util.*;

public class CarManager implements Serializable {
    public ArrayList<Car> cars;

    public ArrayList<User> users;

    public ArrayList<Car> carUsers;

    public IOTextFile<Car> ioTextFile1 = new IOTextFile();
    IOTextFile<User> ioTextFile = new IOTextFile();

    public CarManager() {
        cars = ioTextFile1.readFile("Case_Study2-master/CaseStudy_Module2/src/File/Storage.txt");
        carUsers = ioTextFile1.readFile("Case_Study2-master/CaseStudy_Module2/src/File/CartUser.txt");
        users = ioTextFile.readFile("Case_Study2-master/CaseStudy_Module2/src/File/UserAccount.txt");
    }

    public void add(ArrayList<Category> categories, Scanner scanner) {
        try {
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            System.out.println("Enter price: ");
            Double price = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter quantity: ");
            Integer quantity = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter category: ");
            Category category = getCategoryByIndex(categories, scanner);
            cars.add(new Car(name, price, quantity, category));
            ioTextFile1.writeFile(cars, "Case_Study2-master/CaseStudy_Module2/src/File/Storage.txt");
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }


    public void checkID() {
        for (Car c : carUsers) {
            for (int i = 0; i < cars.size(); i++) {
                if (Objects.equals(c.getId(), cars.get(i).getId())) {
                    cars.remove(i);
                }
            }
        }
    }

    public void update(ArrayList<Category> categories, Scanner scanner) {
        try {
            System.out.println("Enter the courseID you want to update: ");
            Long id = Long.parseLong(scanner.nextLine());
            Car carUpdate;
            if ((carUpdate = checkExist(id)) != null) {
                System.out.println("Enter new name: ");
                String name = scanner.nextLine();
                if (!name.equals("")) {
                    carUpdate.setName(name);
                }
                System.out.println("Enter new price: ");
                String price = scanner.nextLine();
                if (!price.equals("")) {
                    carUpdate.setPrice(Double.parseDouble(price));
                }
                System.out.println("Enter new quantity:");
                String quantity = scanner.nextLine();
                if (!quantity.equals("")) {
                    carUpdate.setQuantity(Integer.parseInt(quantity));
                }
                System.out.println("Enter new category: ");
                Category category;
                if ((category = getCategoryByIndex(categories, scanner)) != null) {
                    carUpdate.setCategory(category);
                }
                ioTextFile1.writeFile(cars, "Case_Study2-master/CaseStudy_Module2/src/File/Storage.txt");
            } else {
                System.err.println("There are no course belong this ID");
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void delete(Scanner scanner) {
        try {
            System.out.println("Enter the courseID you want to delete: ");
            Long id = Long.parseLong(scanner.nextLine());
            Car carDelete;
            if ((carDelete = checkExist(id)) != null) {
                cars.remove(carDelete);
                resetStaticIndex();
            } else {
                System.err.println("There are no course belong this ID");
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void display() {
        System.out.printf("%-10s%-20s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n");
        for (Car car : cars) {
            car.display();
        }
    }

    public void displayCartUser() {
//        ioTextFile1.readFile("CaseStudy_Module2/src/File/Cart.txt");
        System.out.printf("%-10s%-20s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n");
        for (Car car : cars) {
            car.display();
        }
    }

    public void displayCartGuest() {
//        ioTextFile1.readFile("CaseStudy_Module2/src/File/CartUser.txt");
        System.out.printf("%-10s%-20s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n");
        for (Car c : carUsers) {
            c.display();
        }
    }


    public void displayById(Scanner scanner) {
        try {
            System.out.println("Enter the courseID you want to display: ");
            Long id = Long.parseLong(scanner.nextLine());
            Car car;
            if ((car = checkExist(id)) != null) {
                System.out.println(car);
            } else {
                System.err.println("There are no course belong this ID");
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void displayByCategory(ArrayList<Category> categories, Scanner scanner) {
        System.out.println("Enter the category you want to display: ");
        Category category = getCategoryByIndex(categories, scanner);
        if (category != null) {
            for (Car car : cars) {
                if (car.getCategory().equals(category)) {
                    System.out.println(car);
                }
            }
        } else {
            System.err.println("There are no course belong this ID");
        }
    }

    public void displayCourseByNameContaining(Scanner scanner) {
        System.out.println("Enter character you want search: ");
        String search = scanner.nextLine();
        System.out.println("List course have name contains " + search + ": ");
        for (Car course : cars) {
            if (course.getName().contains(search)) {
                System.out.println(course);
            }
        }
    }

    private Category getCategoryByIndex(ArrayList<Category> categories, Scanner scanner) {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        System.out.println("O. No choice!");
        int choice;
        try {
            do {
                System.out.println("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    return null;
                }
                if (choice > 0 && choice <= categories.size()) {
                    return categories.get(choice - 1);
                }
                System.err.println("Please re-enter your selection!");
            } while (choice < 0 || choice > categories.size());
        } catch (InputMismatchException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    private Car checkExist(Long id) {
        for (Car course : cars) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

    public void resetStaticIndex() {
        if (!cars.isEmpty()) {
            Car.INDEX = cars.get(cars.size() - 1).getId();
        }
    }

    public void viewCart() {
        System.out.printf("%-10s%-20s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n");
        for (Car c : carUsers) {
            if (carUsers.isEmpty()) {
                System.out.println("Empty Cart!");
            }
            c.display();
        }
    }


    public void viewBillCart() {
        System.out.printf("%-10s%-20s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n");
        for (Car c : carUsers) {
            c.display();
        }
        Double sum = 0.0;
        for (Car c : carUsers) {
            sum += c.getPrice();
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("%-10s%-20s%-15s%-20s%s", "Total", "", sum, "", "");
        carUsers.clear();
        System.out.println();
    }

    public void addCarByUser(Scanner scanner) {
        try {
            System.out.println("Enter ID car: ");
            Long id = Long.parseLong(scanner.nextLine());
            for (Car car : cars) {
                if (car.getId().equals(id)) {
                    carUsers.add(car);
                    cars.remove(car);
                    System.out.println("Successfully purchase!");
                    break;
                }
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}



