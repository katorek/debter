package com.wjaronski.debter.manager.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "debts")
@Data
public class Debt {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
//    @NotNull
    private UserBean creditor;

    @ManyToOne
//    @NotNull
    private UserBean debtor;

    @NotNull
    private Double amount;

    @Transient
    private boolean toDelete = false;

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
}
