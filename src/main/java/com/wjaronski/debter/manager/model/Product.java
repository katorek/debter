package com.wjaronski.debter.manager.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Wojciech Jaronski
 */

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    private Long productId;
    private String name;
    private Double price;
    //    private User debtor;
//    private User creditor;
    private String creditor;
    private String debtor;
    private Boolean everyonePays;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

//    public String getDebtor() {
//        return debtor.getName();
//    }

//    public String getCreditor() {
//        return creditor.getName();
//    }
}
