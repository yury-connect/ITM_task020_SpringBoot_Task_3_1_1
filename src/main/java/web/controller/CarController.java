package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Car;
import web.service.CarService;
import web.util.CarUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


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
        return "redirect:/cars";
    }




    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("car_list", service.getAll());
        return "cars";
    }

    @GetMapping("/delete")
    public String showDeleteCarPage(@RequestParam("id_removed_car") Integer id, Model model) {
        model.addAttribute("removed_car", service.getById(id));
        return "delete_car_page";
    }
    @PostMapping("/delete")
    public String deleteCar(@RequestParam(name = "id_removed_car") Integer id) {
        service.delete(id);
        return "redirect:/cars";
    }
    @PostMapping("/delete_all")
    public String deleteAllCars() {
//        service.getAll().forEach(car -> service.delete(car.getId())); // так нельзя, возникает 'ConcurrentModificationException' т.к. в этом случае будет попытка модифицировать коллекцию во время её итерации через метод forEach, который не поддерживает изменение исходной коллекции
        List<Car> deleteCarsList = new ArrayList<>(service.getAll());
        deleteCarsList.forEach(car -> service.delete(car.getId()));
        return "redirect:/cars";
    }


    @GetMapping("/edit")
    public String showEditCarPage(@RequestParam("id_edited_car") Integer id, Model model) {
        model.addAttribute("edited_car", service.getById(id));
        return "update_car_page";
    }
    @PostMapping("/edit")
    public String editCar(@ModelAttribute("edited_car") Car car) {
        service.update(car);
        return "redirect:/cars";
    }


    @GetMapping("/create")
    public String showCreateCarPage(Model model) {
        Car defaultCar = CarUtils.generateCar();

        defaultCar.setId(-1);
        defaultCar.setModel("Default_MODEL // " + defaultCar.getModel());
        defaultCar.setColor("Default_COLOR // " + defaultCar.getColor());
        defaultCar.setReleaseDate(new Date(System.currentTimeMillis()));

        model.addAttribute("created_car", defaultCar);
        return "create_car_page";
    }
    @PostMapping("/create")
    public String createCar(@ModelAttribute("created_car") Car car) {
        service.save(car);
        return "redirect:/cars";
    }


    @PostMapping("/generate")
    public String generateCar(@ModelAttribute("car_count") Integer count) {
        CarUtils.generateCar(count).stream().forEach(car -> service.save(car));
        return "redirect:/cars";
    }
}
