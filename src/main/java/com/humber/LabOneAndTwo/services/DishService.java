package com.humber.LabOneAndTwo.services;

import com.humber.LabOneAndTwo.models.Dish;
import com.humber.LabOneAndTwo.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    //Method to get all dishes
    public List<Dish> getDishes() {
        //Business logic tends to go here
        return DishRepository.getDishes();
    }
}
