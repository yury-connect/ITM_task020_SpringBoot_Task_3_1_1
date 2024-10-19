package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Car;
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
        System.out.println("Вывожу все 'Car' из БД: ");             // ******************   УДАЛИТЬ   ******************
        service.getAll().stream().forEach(System.out::println);     // ******************   УДАЛИТЬ   ******************
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

    @GetMapping("/edit")
    public String showEditCarPage(@RequestParam("id_edited_car") Integer id, Model model) {
        model.addAttribute("edited_car", service.getById(id));
        return "edit_car_page";
    }
    @PostMapping("/edit")
    public String editCar(@ModelAttribute("edited_car") Car car) {
        service.update(car);
        return "redirect:/cars";
    }

@PostMapping("/generate")
    public String generateCar(@ModelAttribute("car_count") Integer count) {
        CarUtils.generateCar(count).stream().forEach(car -> service.save(car));
        return "redirect:/cars";
    }
}
