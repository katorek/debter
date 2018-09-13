package com.wjaronski.debter.manager.web.controller.dto;

import com.wjaronski.debter.manager.api.domain.Debt;
import com.wjaronski.debter.manager.api.domain.UserBean;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SimpleDebt {
    @NotNull
    private String debtor;
    @NotNull
    private String creditor;
    @NotNull
    private Double amount;

    public Debt toDebt() {
        Debt debt = new Debt();
        debt.setDebtor(UserBean.getUserOf(debtor));
        debt.setCreditor(UserBean.getUserOf(creditor));
        debt.setAmount(amount);
        return debt;
    }
}
