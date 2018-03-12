package com.aop.utils;

import com.aop.model.Car;

import java.util.List;

public class ObservableList {
    private List<Car> cars;
    private List<Observer<List<Car>>> observers;
    public ObservableList(List<Car> cars) {
        this.cars = cars;
    }
    public void addObserver(Observer<List<Car>> observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer<List<Car>> observer) {
        observers.remove(observer);
    }
    public void notifyObservers(){
        observers.forEach(observer -> observer.update(cars));
    }
}
