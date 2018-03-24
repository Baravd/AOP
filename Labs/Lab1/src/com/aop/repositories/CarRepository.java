package com.aop.repositories;

import com.aop.model.Car;
import com.aop.utils.LoggerSingleton;
import com.aop.utils.Observable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarRepository extends Observable {

    private static final String url = "jdbc:postgresql://localhost:5432/AOP";

    private static final String SOURCE_CLASS = CarRepository.class.getName();

    private final static LoggerSingleton logger = LoggerSingleton.getInstance();

    private final Connection conn;

    public CarRepository()
            throws SQLException {
        super();
        logger.info("new() " + SOURCE_CLASS);
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        conn = DriverManager.getConnection(url, props);
        logger.info("Exiting " + SOURCE_CLASS + " with the following instantiations" + props + ' ' +
                    conn);//no pattern %s as in timber


    }

    public List<Car> findAll()
            throws SQLException {
        logger.info("Entered " + SOURCE_CLASS + "findAll...");
        ArrayList<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM Cars";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("Id");
            String brand = resultSet.getString("brand");
            String model = resultSet.getString("model");
            double price = resultSet.getDouble("price");
            Car car = new Car(id, brand, model, price);
            cars.add(car);
        }
        resultSet.close();
        logger.info("Exiting" + SOURCE_CLASS + "findAll...");

        String message = "Find all returned with \n" + cars;
        notifyObservers(message);
        return cars;
    }

    public Car addCar(Car car)
            throws SQLException {
        logger.info("Entered " + SOURCE_CLASS + "addCar " + "with params=" + car + "...");

        String insertQuery = "INSERT INTO Cars(id,brand,model,price) VALUES(?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(insertQuery);
        statement.setInt(1, car.getId());
        statement.setString(2, car.getBrand());
        statement.setString(3, car.getModel());
        statement.setDouble(4, car.getPrice());
        statement.execute();

        String message = "We added the car " + car;
        notifyObservers(message);
        logger.info("Exiting" + SOURCE_CLASS + "addCar...");
        return car;

    }

    public Car updateCar(Car car)
            throws SQLException {
        logger.info("Entered " + SOURCE_CLASS + "updateCar" + "with params=" + car + "...");

        String updateQuery = "UPDATE Cars SET brand = ?, model = ?, price = ? WHERE id = ? ";
        PreparedStatement statement = conn.prepareStatement(updateQuery);
        statement.setInt(4, car.getId());
        statement.setString(1, car.getBrand());
        statement.setString(2, car.getModel());
        statement.setDouble(3, car.getPrice());
        statement.execute();


        String message = "New values of car with id=" + car.getId() + "  are=" + car;
        notifyObservers(message);


        logger.info("Exiting" + SOURCE_CLASS + "updateCar...");

        return car;
    }

    public int delete(int id)
            throws SQLException {
        logger.info("Entered " + SOURCE_CLASS + "deleteCar " + "with params: id=" + id + "...");

        String deleteQuery = "DELETE FROM Cars WHERE Id = ?";
        PreparedStatement statement = conn.prepareStatement(deleteQuery);
        statement.setInt(1, id);
        statement.execute();


        String message = "Deleted car with id" + id;
        notifyObservers(message);

        logger.info("Exiting" + SOURCE_CLASS + "deleteCar...");

        return id;
    }
}
