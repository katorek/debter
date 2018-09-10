package com.wjaronski.debter.manager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Wojciech Jaronski
 */

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table
public class Bill {
    @Id
    private Long billId;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL)
    private List<User> debtors;

    @Transient
    private String creditor;

    public static Bill getBillOfProducts(List<Product> productList) {
        Bill bill = new Bill();
        bill.products = productList;
        return bill;
    }

    public List<String> getDebtors() {
        return debtors.stream().map(User::getName).collect(Collectors.toList());
    }

    public void setDebtors(List<String> debtors) {
        this.debtors = debtors.stream().map(User::getUserOf).collect(Collectors.toList());
    }

    public List<User> getDebtorsUsers() {
        return debtors;
    }

}
