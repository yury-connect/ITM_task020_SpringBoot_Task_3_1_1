package web.service;

import web.model.Car;

import java.util.List;


public interface CarService {

    void save(Car car);

    Car getById(int id);

    List<Car> getAll();

    List<Car> get(Integer count);

    void update(Car car);

    void delete(int id);
}
