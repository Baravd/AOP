package com.aop;

import com.aop.controllers.CarController;
import com.aop.repositories.CarRepository;
import com.aop.ui.ConsoleUI;

import java.sql.SQLException;

public class Main {

    private static final String SOURCE_CLASS = Main.class.getName();



    public static void main(String[] args)
            throws SQLException {

        CarRepository carRepository = new CarRepository();
        CarController carController = new CarController(carRepository);
        ConsoleUI consoleUI = new ConsoleUI(carController);
        //carRepository.addObserver(consoleUI);
        consoleUI.run();


    }
}
