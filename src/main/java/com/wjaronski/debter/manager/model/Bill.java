package com.wjaronski.debter.manager.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Wojciech Jaronski
 */

@Data
public class Bill {
    private List<Product> products;
    private List<String> debtors;
    private String creditor;

    public static Bill getBillOfProducts(List<Product> productList) {
        Bill bill = new Bill();
        bill.products = productList;
        return bill;
    }
}
