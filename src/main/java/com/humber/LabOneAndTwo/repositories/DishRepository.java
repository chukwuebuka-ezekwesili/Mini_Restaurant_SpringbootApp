package com.humber.LabOneAndTwo.repositories;

import com.humber.LabOneAndTwo.models.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishRepository {

    private static List<Dish> dishes = new ArrayList<Dish>();

    //Create some data of dishes
    static{
        //dishes.add(new Dish (1, "Pizza", "Veg", 10.0));
        dishes.add(
                Dish.builder().id(1).name("Burger").category("Non-Veg").price(5.0).build()
        );
        dishes.add(
                Dish.builder().id(2).name("Shawarma").category("Non-Veg").price(8.0).build()
        );
        dishes.add(
                Dish.builder().id(3).name("Poutine").category("Veg").price(12.0).build()
        );
    }

    //Method to return dish data
    public static List<Dish> getDishes() {
        return dishes;
    }
}
