package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;


@Repository
public class CarDaoImpl implements CarDao {

    private List<Car> cars;


    public CarDaoImpl() {
        this.cars = new ArrayList<>();
    }


    @Override
    public void save(Car car) {
        int id = cars.size() == 0 ? 0 : cars.get(cars.size() - 1).getId() + 1;
        car.setId(id);
        cars.add(car);
    }

    @Override
    public Car getById(int id) {
        Car car = cars.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        return car;
    }

    @Override
    public List<Car> getAll() {
        return cars;
    }

    @Override
    public void update(Car car) {
        Car updated = cars.stream().filter(item -> item.getId() == car.getId()).findFirst().get();
        updated.setModel(car.getModel());
        updated.setColor(car.getColor());
        updated.setReleaseDate(car.getReleaseDate());
    }

    @Override
    public void delete(int id) {
        cars.removeIf(c -> c.getId() == id); // удаляет все элементы, удовлетворяющие условию
    }
}
