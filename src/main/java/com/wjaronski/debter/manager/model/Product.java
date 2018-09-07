package com.wjaronski.debter.manager.model;

import lombok.Data;

/**
 * Created by Wojciech Jaronski
 */

@Data
public class Product {

    private String name;
    private double price;
    private String debtor;
    private String creditor;
    private boolean everyonePays;
}
