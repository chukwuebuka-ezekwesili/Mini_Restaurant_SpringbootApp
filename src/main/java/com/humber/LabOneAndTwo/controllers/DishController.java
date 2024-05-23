package com.humber.LabOneAndTwo.controllers;

import com.humber.LabOneAndTwo.models.Dish;
import com.humber.LabOneAndTwo.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //this method will open up add dish page
    //open up add-dish page
    @GetMapping("/add-dish")
    public String addDish(Model model) {
        model.addAttribute("dish", new Dish());
        return "add-dish";
    }

    // Handles POST requests to "/add-dish" endpoint, This method processes the form submission for adding a new dish
    //this is trying to save the dish inputs
    @PostMapping("/add-dish")
    public String saveDish(@ModelAttribute Dish dish, Model model) {
        //saving in the db
        //this is how to save - dishService.saveDish(dish)

        // this is the business error message to check the price, this checks that the price of dish should not be greater than 10
        // And if it is, it should display an error message
        if(dish.getPrice()>10){
            model.addAttribute("error", "Price should be less than 10");
            return "add-dish";
        }

        //adds the dish to the model to display it on hte menu page, and then returns the view name "menu" to be rendered
        model.addAttribute("dishes", dish);
        return "menu";
    }

}
