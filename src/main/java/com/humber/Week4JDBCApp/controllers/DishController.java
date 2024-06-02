package com.humber.Week4JDBCApp.controllers;

import com.humber.Week4JDBCApp.models.Dish;
import com.humber.Week4JDBCApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getAllDishes(Model model, @RequestParam(required = false) String message){
        //Injects dishes into the html > whatever is in model is available to the view
        model.addAttribute("dishes", dishService.getDishes());
        model.addAttribute("message", "Dish added successfully");
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
    public String addDish(@ModelAttribute Dish dish, Model model) {

        // Try to save the dish using the DishService. This may throw an IllegalArgumentException
        // if the business rule (price less than 20) is violated.
        try {
            dishService.saveDish(dish);
        } catch (IllegalArgumentException e) {
            // Catch the IllegalArgumentException thrown by DishService if the dish price is greater than 10.
            // Add the error message to the model to be displayed on the "add-dish" page.
            // Return the "add-dish" view to allow the user to correct the input.
            model.addAttribute("error", e.getMessage());
            return "add-dish";
        }
        // If the dish is saved successfully, redirect to the "/restaurant/dishes" endpoint
        // with a success message.
        return "redirect:/restaurant/dishes?message=Dish added successfully";
    }

//        //saving in the db
//        //this is how to save - dishService.saveDish(dish)
//        dishService.saveDish(dish);
//
//        // this is the business error message to check the price, this checks that the price of dish should not be greater than 10
//        // And if it is, it should display an error message
//        if(dish.getPrice()>10){
//            model.addAttribute("error", "Price should be less than 10");
//            return "add-dish";
//        }
//
//
//        //adds the dish to the model to display it on hte menu page, and then returns the view name "menu" to be rendered
//        //modified to return everything added and the database values also
////        model.addAttribute("dishes", dishService.getDishes());
////        model.addAttribute("message", "Dish added successfully");
////        return "menu";
//
//        // in order to avoid repeating our codes, the DRY METHOD
//        return "redirect:/restaurant/dishes?message=Dish added successfully";
    }


