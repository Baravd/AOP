package com.aop.controllers;

import com.aop.model.Car;
import com.aop.repositories.CarRepository;
import com.aop.utils.LoggerSingleton;

import java.sql.SQLException;
import java.util.List;

public class CarController {

    private static final String SOURCE_CLASS = CarController.class.getName();

    private final static LoggerSingleton logger = LoggerSingleton.getInstance();

    private final CarRepository carRepository;


    public CarController(CarRepository carRepository) {
        //logger.info("new() " + SOURCE_CLASS);//no pattern %s as in timber
        this.carRepository = carRepository;
    }

    public List<Car> findAll()
            throws SQLException {
        logger.info("Entered " + SOURCE_CLASS + "findAll...");
        logger.info("Exiting" + SOURCE_CLASS + "findAll...");
        return carRepository.findAll();
    }

    public Car addCar(Car car)
            throws SQLException {
        logger.info("Entered " + SOURCE_CLASS + "addCar " + "with params=" + car + "...");
        logger.info("Exiting" + SOURCE_CLASS + "addCar...");

        return carRepository.addCar(car);
    }

    public Car updateCar(Car car)
            throws SQLException {
        logger.info("Entered " + SOURCE_CLASS + "updateCar" + "with params=" + car + "...");
        logger.info("Exiting" + SOURCE_CLASS + "updateCar...");

        return carRepository.updateCar(car);
    }

    public int deleteCar(int id)
            throws SQLException {
        logger.info("Entered " + SOURCE_CLASS + "deleteCar " + "with params: id=" + id + "...");
        logger.info("Exiting" + SOURCE_CLASS + "deleteCar...");

        return carRepository.delete(id);
    }
}
