package com.humber.Week8Security.controllers;

import com.humber.Week8Security.models.Dish;
import com.humber.Week8Security.services.DishService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/restaurant/admin")
public class AdminController {

    // field injection - injecting service into controller
    private final DishService dishService;

    @Value("Five Star Restaurant")
    private String name;

    // constructor injection
    public AdminController(DishService dishService) {
        this.dishService = dishService;
    }

    //this method will open up add dish page
    //open up add-dish page
    @GetMapping("/add-dish")
    public String addDish(Model model) {
        model.addAttribute("dish", new Dish());
        return "admin/add-dish";
    }

    @PostMapping("/add-dish")
    public String addDish(@ModelAttribute Dish dish, Model model) {


        //saving in the db
        int statusCode = dishService.saveDish(dish);

        //1 fo success, 0 for failure
        if(statusCode == 1){
            return "redirect:/restaurant/menu/1?message=Dish added successfully";
        }

        model.addAttribute("error", "Price should be less than 15");
//        return "redirect:/restaurant/menu?message=Dish was not added";
        return "admin/add-dish";
    }

    // delete a dish
    @GetMapping("delete/{id}")
    public String deleteDish(@PathVariable int id){
        int deleteStatusCode = dishService.deleteDish(id);

        if (deleteStatusCode == 1) {
            return "redirect:/restaurant/menu/1?message=Dish deleted successfully";
        }

        return "redirect:/restaurant/menu/1?message=Dish to be deleted does not exist";
    }

    //update a dish (open the update form)
    @GetMapping("/update/{id}")
    public String updateDish(Model model, @PathVariable int id){
        Optional<Dish> dishToUpdate = dishService.getDishById(id);
        model.addAttribute("dish", dishToUpdate.orElse(null));
        return "admin/add-dish";
    }

    //update a dish
    @PostMapping("/update-dish")
    public String updateDish(@ModelAttribute Dish dish) {
        dishService.updateDish(dish);
        return "redirect:/restaurant/menu/1?message=Dish Updated Successfully!";
    }

}
