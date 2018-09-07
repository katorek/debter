package com.wjaronski.debter.manager.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Wojciech Jaronski
 */

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"debtor", "creditor"})
})
@Data
public class Debt {
    @Id
    @GeneratedValue
    private Long id;
    private String debtor;
    private String creditor;
    private double amount;

    public static Debt getReversed(Debt debt) {
        Debt reversed = new Debt();
        reversed.amount = debt.amount;
        reversed.creditor = debt.debtor;
        reversed.debtor = debt.creditor;
        return reversed;
    }

    public double updateAmount(double amount) {
        return this.amount += amount;
    }
}
