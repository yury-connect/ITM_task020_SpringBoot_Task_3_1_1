import web.model.Car;
import web.util.CarUtils;

import java.util.List;



public class Testes {
    public static void main(String[] args) {

        List<Car> cars = CarUtils.generateCar(10);
        cars.forEach(System.out::println);
    }
}
