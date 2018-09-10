package com.wjaronski.debter.manager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Wojciech Jaronski
 */

@Data
@Entity
@Table
public class Bill {
    @Id
    private Long billId;

    @OneToMany(mappedBy = "bill")
    private List<Product> products;

    //    @ElementCollection
//    @CollectionTable(name = "users", joinColumns = @JoinColumn(name = "name"))
//    @Column(name = "debtors")
    @OneToMany
    private List<User> debtors;
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
    //    public String  getCreditor() {
//        return creditor.getName();
//    }
//
//    public void setCreditor(String creditor) {
//        this.creditor = new User(creditor);
//    }
}
