package com.wjaronski.debter.manager.model;

import lombok.Data;

import javax.persistence.*;
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

    @ManyToOne
    private User debtor;
//    private String debtor;

    //    @Column(name = "creditor", columnDefinition = "VARCHAR(50)")
    @ManyToOne
//    private String creditor;
    private User creditor;
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
}
