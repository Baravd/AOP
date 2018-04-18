package com.aop.controllers;

import com.aop.model.Car;
import com.aop.repositories.CarRepository;

import java.sql.SQLException;
import java.util.List;

public class CarController {


    private static final String SOURCE_CLASS = CarController.class.getName();


    private final CarRepository carRepository;


    public CarController(CarRepository carRepository) {
        //logger.info("new() " + SOURCE_CLASS);//no pattern %s as in timber
        this.carRepository = carRepository;
    }

    public List<Car> findAll()
            throws SQLException {

        return carRepository.findAll();
    }

    public Car addCar(Car car)
            throws SQLException {


        return carRepository.addCar(car);
    }

    public Car updateCar(Car car)
            throws SQLException {


        return carRepository.updateCar(car);
    }

    public int deleteCar(int id)
            throws SQLException {

        return carRepository.delete(id);
    }
}
