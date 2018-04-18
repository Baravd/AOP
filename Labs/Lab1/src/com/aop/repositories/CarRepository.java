package com.aop.repositories;

import com.aop.model.Car;
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

public class CarRepository {//extends Observable {

    private static final String url = "jdbc:postgresql://localhost:5432/AOP";

    private static final String SOURCE_CLASS = CarRepository.class.getName();


    private final Connection conn;

    public CarRepository()
            throws SQLException {
        super();
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        conn = DriverManager.getConnection(url, props);


    }

    public List<Car> findAll()
            throws SQLException {
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

        String message = "Find all returned with \n" + cars;
        //notifyObservers(message);
        return cars;
    }

    public Car addCar(Car car)
            throws SQLException {

        String insertQuery = "INSERT INTO Cars(id,brand,model,price) VALUES(?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(insertQuery);
        statement.setInt(1, car.getId());
        statement.setString(2, car.getBrand());
        statement.setString(3, car.getModel());
        statement.setDouble(4, car.getPrice());
        statement.execute();

        String message = "We added the car " + car;
        //notifyObservers(message);
        return car;

    }

    public Car updateCar(Car car)
            throws SQLException {

        String updateQuery = "UPDATE Cars SET brand = ?, model = ?, price = ? WHERE id = ? ";
        PreparedStatement statement = conn.prepareStatement(updateQuery);
        statement.setInt(4, car.getId());
        statement.setString(1, car.getBrand());
        statement.setString(2, car.getModel());
        statement.setDouble(3, car.getPrice());
        statement.execute();


        String message = "New values of car with id=" + car.getId() + "  are=" + car;
       // notifyObservers(message);



        return car;
    }

    public int delete(int id)
            throws SQLException {

        String deleteQuery = "DELETE FROM Cars WHERE Id = ?";
        PreparedStatement statement = conn.prepareStatement(deleteQuery);
        statement.setInt(1, id);
        statement.execute();


        String message = "Deleted car with id" + id;
        //notifyObservers(message);


        return id;
    }
}
