package com.wjaronski.debter.manager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Wojciech Jaronski
 */

@Entity
//@Table(uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"debtor", "creditor"})
//})
@Table
@Data
public class Debt {
    @Id
    @GeneratedValue
    private Long id;

    //    @Column(name = "debtor", columnDefinition = "VARCHAR(50)")
//    private User debtor;
    private String debtor;

    //    @Column(name = "creditor", columnDefinition = "VARCHAR(50)")
//    private User creditor;
    private String creditor;
    @NotNull
    private Double amount;

    public static Debt getReversed(Debt debt) {
        Debt reversed = new Debt();
        reversed.id = debt.id;
        reversed.amount = debt.amount * -1;
        reversed.creditor = debt.debtor;
        reversed.debtor = debt.creditor;
        return reversed;
    }

    public void updateAmount(Double amount) {
        this.amount += amount;
    }

    public void reverseAmount() {
        amount *= -1;
    }

//    public String  getDebtor() {
//        return debtor.getName();
//    }
//
//    public String getCreditor() {
//        return creditor.getName();
//    }
//
//    public void setDebtor(String  debtor) {
//        this.debtor = new User(debtor);
//    }
//
//    public void setCreditor(String creditor) {
//        this.creditor = new User(creditor);
//    }
}
