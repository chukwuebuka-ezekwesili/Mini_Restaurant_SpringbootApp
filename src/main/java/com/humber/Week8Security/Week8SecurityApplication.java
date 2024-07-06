package com.humber.Week8Security;

import com.humber.Week8Security.models.Dish;
import com.humber.Week8Security.services.DishService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week8SecurityApplication implements CommandLineRunner {

	// injecting service - constructor injection
	private final DishService dishService;

	public Week8SecurityApplication(DishService dishService) {
		this.dishService = dishService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Week8SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Hello from command line");

		dishService.saveDish(new Dish(1, "Pizza", "Non-Veg", 12.0));
		dishService.saveDish(new Dish(2, "Burger", "Non-Veg", 10.0));
		dishService.saveDish(new Dish(3, "Pasta", "Veg", 8.0));
		dishService.saveDish(new Dish(4, "Fried Rice", "Veg", 7.0));
		dishService.saveDish(new Dish(5, "Chicken Biryani", "Non-Veg", 9.0));
		dishService.saveDish(new Dish(6, "Paneer Tikka", "Veg", 9.0));
		dishService.saveDish(new Dish(7, "Fish Curry", "Non-Veg", 13.0));
		dishService.saveDish(new Dish(8, "Mushroom Soup", "Veg", 6.0));
		dishService.saveDish(new Dish(9, "Beef Steak", "Non-Veg", 20.0));
		dishService.saveDish(new Dish(10, "Veg Sandwich", "Veg", 5.0));
		dishService.saveDish(new Dish(11, "Chicken Sandwich", "Non-Veg", 6.0));
		dishService.saveDish(new Dish(12, "Cheese Pizza", "Veg", 11.0));
		dishService.saveDish(new Dish(13, "Pepperoni Pizza", "Non-Veg", 14.0));

//		System.out.println("Done inputting all");

	}
}
