package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CarDao;
import web.model.Car;

import java.util.List;


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

    @Override
    public void update(Car car) {
        dao.update(car);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
