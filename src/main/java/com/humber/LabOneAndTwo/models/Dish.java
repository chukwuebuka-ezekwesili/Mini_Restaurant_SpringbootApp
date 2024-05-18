package com.humber.LabOneAndTwo.models;

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

public class Dish {

    //Fields
    private int id;
    private String name;
    private String category;
    private Double price;

    //This class should match a table in the database with the following fields/columns


}