package com.humber.Week4JDBCApp.services;

import com.humber.Week4JDBCApp.models.Dish;
import com.humber.Week4JDBCApp.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {


    @Autowired
    private DishRepository dishRepository;

    //Method to get all dishes
    public List<Dish> getDishes() {
        //Business logic tends to go here
        return dishRepository.getDishes();
    }

    // SAVE A DISH
    public int saveDish(Dish dish) {
        // Check the business rule: price must be less than or equal to 10.
        // If the price is greater than 10, throw an IllegalArgumentException with an appropriate message.
        if (dish.getPrice() > 20) {
            throw new IllegalArgumentException("Price should be less than $20");
        }
        // If the price is valid, save the dish using the repository.
        return dishRepository.save(dish);
    }
}
