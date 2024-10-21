package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.Service;
import web.util.UserUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Controller
@RequestMapping(value = "/users")
public class Controller {

    private Service service;


    @Autowired
    public Controller(Service service) {
        this.service = service;
    }



    @GetMapping("/generate")
    public String generateUsers(@RequestParam(name = "count_generated_users", required = false, defaultValue = "1") Integer count) {
        System.out.println("generateUsers " + count); // ****************************   УДАЛИТЬ   ****************************
        UserUtils.generateUsers(count).stream().forEach(usr -> service.save(usr));
        return "redirect:/users/all";
    }


    @GetMapping("/all")
    public String showAllPage(Model model) {
        model.addAttribute("all_existing_users", service.getAll());

        System.out.println("showAllPage "); // ****************************   УДАЛИТЬ   ****************************
        service.getAll().stream().forEach(System.out::println); // ****************************   УДАЛИТЬ   ****************************

        return "all_users";
    }

    @GetMapping("/view")
    public String showUserPage(@RequestParam("id_viewed_user") Integer id, Model model) {
        model.addAttribute("viewed_user", service.getById(id));

        System.out.println("showUserPage "); // ****************************   УДАЛИТЬ   ****************************
        System.out.println("viewed_user = " + service.getById(id)); // ****************************   УДАЛИТЬ   ****************************

        return "view_user_page";
    }











    @GetMapping("/delete")
    public String showDeleteUsersPage(@RequestParam("id_removed_user") Integer id, Model model) {
        model.addAttribute("removed_user", service.getById(id));
        return "delete_user_page";
    }
    @PostMapping("/delete")
    public String deleteUsers(@RequestParam(name = "id_removed_user") Integer id) {
        service.delete(id);
        return "redirect:/users/all";
    }
    @PostMapping("/delete_all")
    public String deleteAllUsers() {
        List<User> deleteUsersList = new ArrayList<>(service.getAll());
        deleteUsersList.forEach(usr -> service.delete(usr.getId()));
        return "redirect:/users/all";
    }


    @GetMapping("/edit")
    public String showEditUsersPage(@RequestParam("id_edited_user") Integer id, Model model) {
        model.addAttribute("edited_user", service.getById(id));

        System.out.println("showEditUsersPage : " + service.getById(id)); // ****************************   УДАЛИТЬ   ****************************

        return "update_user_page";
    }
    @PostMapping("/edit")
    public String editUsers(@ModelAttribute("edited_user") User user) {
        service.update(user);
        return "redirect:/users/all";
    }


    @GetMapping("/create")
    public String showCreateUsersPage(Model model) {
        User defaultUser = UserUtils.generateUser();

        defaultUser.setId(-1);
        defaultUser.setUserName("Default_UserName // " + defaultUser.getUserName());
        defaultUser.setEmail("Default_Email // " + defaultUser.getEmail());
        defaultUser.setFullName("Default_UserName // " + defaultUser.getFullName());
        defaultUser.setDateBirth(new Date(System.currentTimeMillis()));
        defaultUser.setAddress("Default_Address // " + defaultUser.getAddress());

        model.addAttribute("created_user", defaultUser);
        return "create_user_page";
    }
    @PostMapping("/create")
    public String createUser(@ModelAttribute("created_user") User user) {
        service.save(user);
        return "redirect:/users/all";
    }


//    @PostMapping("/generate")
//    public String generateUsers(@ModelAttribute("car_count") Integer count) {
//        UserUtils.generateCar(count).stream().forEach(car -> service.save(car));
//        return "redirect:/cars";
//    }
}
