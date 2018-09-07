package com.wjaronski.debter.manager.model;

import lombok.Data;

/**
 * Created by Wojciech Jaronski
 */

@Data
public class Product {

    private String name;
    private Double price;
    private String debtor;
    private String creditor;
    private Boolean everyonePays;
    //todo check how many clients pay for all products and split equally
}
