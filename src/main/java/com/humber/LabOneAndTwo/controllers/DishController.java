package com.humber.LabOneAndTwo.controllers;

import com.humber.LabOneAndTwo.models.Dish;
import com.humber.LabOneAndTwo.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller

//Create a base path
@RequestMapping("/restaurant")

public class DishController {

    @Autowired
    //field injection - injecting service into controller - not recommended
    //must make DishService class a bean using @Service/@Component
    private DishService dishService;

    @Value("Five Star Restaurant")
    private String name;

    //must define endpoint for each method - endpoint: "/home"
    //method displays home in json data
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("restaurantName", name);
        return "home"; //refers to the view/html file
    }





    //    @GetMapping("/dishes")
//    public List<Dish> getAllDishes(){
//        return dishService.getDishes();
//    }
    //method to get all dishes
    @GetMapping("/dishes")
    public String getAllDishes(Model model){
        //Injects dishes into the html > whatever is in model is available to the view
        model.addAttribute("dishes", dishService.getDishes());
        return "menu";
    }

}
