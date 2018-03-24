package com.aop;

import com.aop.controllers.CarController;
import com.aop.repositories.CarRepository;
import com.aop.ui.ConsoleUI;
import com.aop.utils.LoggerSingleton;

import java.sql.SQLException;

public class Main {

    private static final String SOURCE_CLASS = Main.class.getName();

    private final static LoggerSingleton logger = LoggerSingleton.getInstance();


    public static void main(String[] args)
            throws SQLException {
        logger.info("Entered " + SOURCE_CLASS + "main...");

        CarRepository carRepository = new CarRepository();
        CarController carController = new CarController(carRepository);
        ConsoleUI consoleUI = new ConsoleUI(carController);
        carRepository.addObserver(consoleUI);
        consoleUI.run();

        logger.info("Exiting" + SOURCE_CLASS + "main...");

    }
}
