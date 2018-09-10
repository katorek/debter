package com.wjaronski.debter.manager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Wojciech Jaronski
 */

@Data
@Entity
@Table(name = "BILLS")
public class Bill {
    @Id
    private Long id;

    @OneToMany(mappedBy = "bill")
    private List<Product> products;

    @ElementCollection
    @CollectionTable(name = "Users", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "debtors")
    private List<String> debtors;
    private String creditor;

    public static Bill getBillOfProducts(List<Product> productList) {
        Bill bill = new Bill();
        bill.products = productList;
        return bill;
    }

//    public String  getCreditor() {
//        return creditor.getName();
//    }
//
//    public void setCreditor(String creditor) {
//        this.creditor = new User(creditor);
//    }
}
