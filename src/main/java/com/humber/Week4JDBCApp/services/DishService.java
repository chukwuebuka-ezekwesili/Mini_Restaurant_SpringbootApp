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
        return dishRepository.save(dish);
    }
}
