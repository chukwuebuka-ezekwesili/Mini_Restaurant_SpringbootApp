package com.humber.Week8Security.controllers;

import com.humber.Week8Security.models.Dish;
import com.humber.Week8Security.services.DishService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

//Create a base path
@RequestMapping("/restaurant")

public class DishController {


    // field injection - injecting service into controller
    private final DishService dishService;

    @Value("Five Star Restaurant")
    private String name;

    @Value("${page.size}")
    private int pageSize;

    // constructor injection
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    //must define endpoint for each method - endpoint: "/home"
    //method displays home in json data
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("restaurantName", name);
        return "home"; //refers to the view/html file
    }



    @GetMapping("/menu/{pageNo}")
    public String getAllDishes(Model model, @RequestParam(required = false) String message,
                               @RequestParam(required = false) String searchedCategory,
                               @RequestParam(required = false) Double searchedPrice,
                               @PathVariable int pageNo,
                               @RequestParam(required = false) String sortField,
                               @RequestParam(required = false) String sortDirection) {

        // Default sortField and sortDirection
        sortField = sortField == null ? "id" : sortField;
        sortDirection = sortDirection == null ? "asc" : sortDirection;

        // Retrieve paginated dishes or filtered dishes based on search parameters
        Page<Dish> page;
        if (searchedCategory != null || searchedPrice != null) {
            List<Dish> filteredDishes = dishService.getFilteredDishes(searchedCategory, searchedPrice);
            model.addAttribute("dishes", filteredDishes);
            model.addAttribute("totalPages", 1); // Set a default totalPages when filtered
        } else {
            page = dishService.getPaginatedDishes(pageSize, pageNo, sortField, sortDirection);
            model.addAttribute("dishes", page.getContent());
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalItems", page.getTotalElements());
        }

        // Sorting info
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

        // Search parameters
        model.addAttribute("searchedCategory", searchedCategory);
        model.addAttribute("searchedPrice", searchedPrice);

        model.addAttribute("message", message);
        return "menu";
    }


    //this method will open up add dish page
    //open up add-dish page
    @GetMapping("/add-dish")
    public String addDish(Model model) {
        model.addAttribute("dish", new Dish());
        return "add-dish";
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
        return "add-dish";
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
        return "add-dish";
    }

    //update a dish
    @PostMapping("/update-dish")
    public String updateDish(@ModelAttribute Dish dish) {
        dishService.updateDish(dish);
        return "redirect:/restaurant/menu/1?message=Dish Updated Successfully!";
    }


}


