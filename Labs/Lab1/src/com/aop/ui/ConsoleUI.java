package com.aop.ui;

import com.aop.controllers.CarController;
import com.aop.model.Car;
import com.aop.utils.LoggerSingleton;
import com.aop.utils.Observer;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements Observer<List<Car>> {

    private static final String SOURCE_CLASS = ConsoleUI.class.getName();

    private final static LoggerSingleton logger = LoggerSingleton.getInstance();

    private final CarController carController;

    private final Scanner scanner = new Scanner(System.in);

    private List<Car> cars;

    public ConsoleUI(CarController carController)
            throws SQLException {
        logger.info("new() " + SOURCE_CLASS);

        this.carController = carController;
        cars = carController.findAll();

    }

    public void run() {
        logger.info("Entered " + SOURCE_CLASS + "run...");

        boolean exit = false;
        Car car;
        do {
            printMenu();
            String selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case "1":
                    car = readAllParams();

                    try {
                        validateCar(car);
                        carController.addCar(car);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Please give the id (only ints are accepted) ");
                    int id = scanner.nextInt();

                    try {
                        int i = carController.deleteCar(id);
                        System.out.printf("Car %d was deleted\n", i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    car = readAllParams();

                    try {
                        //lasam fara check la id sa vedem logarea
                        carController.updateCar(car);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    try {
                        System.out.println(carController.findAll());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!exit);

        logger.info("Exiting " + SOURCE_CLASS + "run...");

    }

    private void validateCar(Car car)
            throws Exception {
        boolean b = carController.findAll().stream().anyMatch(car1 -> car1.getId() == car.getId());
        if (b) {
            throw new Exception("Id already exists!!!");
        }
    }

    private Car readAllParams() {
        System.out.println("Id=");
        Integer id = Integer.valueOf(scanner.nextLine());

        System.out.println("Brand=");
        String brand = scanner.nextLine();

        System.out.println("Model=");
        String model = scanner.nextLine();

        System.out.println("Price=");
        Double price = Double.valueOf(scanner.nextLine());
        return new Car(id, brand, model, price);
    }

    private void printMenu() {
        String options = "\n1. Add Car \n";
        options += "2. Delete Car\n";
        options += "3. Update Car\n";
        options += "4. Print all Cars\n";
        options += "0. Exit\n";
        System.out.println(options);
    }


    @Override
    public void update(List<Car> cars) {
        logger.info("Entered " + SOURCE_CLASS + "update overridden method...");
        this.cars = cars;
    }
}
