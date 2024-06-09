package com.humber.Week5JPA.services;

import com.humber.Week5JPA.models.Dish;
import com.humber.Week5JPA.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

//    // field injection which we dnt need anymore
//    @Autowired
//    private DishRepository dishRepository;
//
    private final DishRepository dishRepository;

    //constructor injection
    public DishService(DishRepository dishRepository){
        this.dishRepository = dishRepository;
    }

    //Method to get all dishes
    public List<Dish> getDishes() {
        //Business logic tends to go here
        return dishRepository.findAll();
    }

    // SAVE A DISH
    public int saveDish(Dish dish) {

        if(dish.getPrice() < 15){
            dishRepository.save(dish);
            return 1;
        }
        return 0;
    }

    // get filtered dishes
    public List<Dish> getFilteredDishes(String searchedCategory, Double searchedPrice) {
        return dishRepository.findByCategoryAndPrice(searchedCategory, searchedPrice);
    }

    //delete a dish
    public int deleteDish(int id){
        // check if the record exists and then only perform the delete operation
        if(dishRepository.existsById(id)){
            dishRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    //update a dish
    public void updateDish(Dish dish) {
        dishRepository.save(dish);
    }

    // get a dish by id
    public Optional<Dish> getDishById(int id){
        return dishRepository.findById(id);
    }
}
