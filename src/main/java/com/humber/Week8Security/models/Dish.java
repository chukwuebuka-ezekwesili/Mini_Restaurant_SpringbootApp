package com.humber.Week8Security.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//This creates getters and setters - Or alternative @Getter and @Setter
//To see getters/setters > maven> compile | target folder> classes>com>humber>nameOfApp>Models>Class
//To get jar file >maven> install>run | test-classes folder >jar file
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Fields
    private int id;
    private String name;
    private String category;
    private Double price;

    //This class should match a table in the database with the following fields/columns


}