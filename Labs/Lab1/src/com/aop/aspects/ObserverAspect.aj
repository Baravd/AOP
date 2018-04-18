package com.aop.aspects;

import com.aop.repositories.CarRepository;
import com.aop.ui.ConsoleUI;
import com.aop.utils.Observer;
import com.aop.utils.Subject;

import java.util.List;

public aspect ObserverAspect {
    declare parents:CarRepository implements Subject;
    declare parents:ConsoleUI implements Observer;

    private List<Observer> Subject.observers;

    public void Subject.addObserver(Observer obs) {
        System.out.println("Adding observer");
        observers.add(obs);
    }
    public void Subject.removeObserver(Observer obs) {
        System.out.println("Removing observer");
        observers.remove(obs);

    }
    public void Subject.notifyObservers(Object o) {
        observers.forEach((observer) -> observer.update(o));
    }

    pointcut observed(CarRepository repo):execution(public * com.aop.repositories.CarRepository.*(..)) && this(repo);

    private CarRepository carRepository;
    after(CarRepository repo, ConsoleUI ui): initialization(com.aop.ui.ConsoleUI.new(*)) &&this(ui) &&this(repo){
        repo.addObserver(ui);
        carRepository = repo;
    }
    after(CarRepository repo) returning: observed(repo){
        System.out.println("!!!!Inside ObserverAspect: notifyObservers!!!!!");
        repo.notifyObservers(new Object());
    }
    public void ConsoleUI.update(Object o){
        System.out.println("Inside ObserverAspect: ViewResultsHandler.update ");

    }








}
