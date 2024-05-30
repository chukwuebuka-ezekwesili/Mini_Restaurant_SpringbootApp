package com.humber.Week4JDBCApp.repositories;

import com.humber.Week4JDBCApp.models.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DishRepository {


    @Autowired
    JdbcTemplate template;

    // SAVE A DISH;

    public int save(Dish dish) {
        String sql = "INSERT INTO dish(name, category, price) VALUES (?, ?, ?)";
        return template.update(sql, dish.getName(), dish.getCategory(), dish.getPrice());
    }



    // GET DISH METHOD
    public List<Dish> getDishes() {
        String sql = "SELECT * FROM dish";

        RowMapper<Dish> mapper = new RowMapper<Dish>() {

            @Override
            public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
                Dish myDish = new Dish();

                myDish.setId(rs.getInt(1));
                myDish.setName(rs.getString(2));
                myDish.setCategory(rs.getString(3));
                myDish.setPrice(rs.getDouble(4));


                return myDish;
            }
        };

        List<Dish> dishes = template.query(sql, mapper);
        return dishes;
    }



}
