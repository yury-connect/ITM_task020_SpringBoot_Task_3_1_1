package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CarDao;
import web.model.Car;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarServiceImpl implements CarService{

    CarDao dao;


    @Autowired
//    @Qualifier("carDaoImpl")
    public CarServiceImpl(CarDao carDao) {
        this.dao = carDao;
    }



    @Override
    public void save(Car car) {
        dao.save(car);
    }


    @Override
    public Car getById(int id) {
        return dao.getById(id);
    }


    @Override
    public List<Car> getAll() {
        return dao.getAll()  ;
    }


    /*
    При запросе /cars выводить весь список. При запросе /cars?count=2
    должен отобразиться список из 2 машин, при /cars?count=3 - из 3, и тд.
    При count ≥ 5 выводить весь список машин.
     */
    @Override
    public List<Car> get(Integer count) {
        List<Car> result;
        if (count == null || count >= 5) {
            result = dao.getAll();
        } else {
            result = dao.getAll().stream().limit(count).collect(Collectors.toList());
        }
        return result;
    }


    @Override
    public void update(Car car) {
        dao.update(car);
    }


    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
