package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.CarService;
import web.util.CarUtils;


@Controller
@RequestMapping(value = "/cars")
public class CarController {

    CarService service;


    @Autowired
    public CarController(CarService service) {
        this.service = service;
    }


    @GetMapping("/{count}")
    public String generateCars(@PathVariable Integer count, Model model) {
        CarUtils.generateCar(count).stream().forEach(car -> service.save(car));

        service.getAll().forEach(car -> System.out.println(car));
        return "hello";
    }
}
