package com.wjaronski.debter.manager.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wjaronski.debter.manager.api.domain.Debt;
import com.wjaronski.debter.manager.api.domain.UserBean;
import lombok.Data;

/**
 * Created by wojta
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DebtDto {
    private String debtorName;
    private String creditorName;

    private UserBean debtor;
    private UserBean creditor;
    private Double amount;

    public DebtDto(Debt debt) {
//        debtor = new UserDto(debt.getDebtor());
//        creditor = new UserDto(debt.getCreditor());
        debtor = (debt.getDebtor());
        debtorName = debtor.getName();
        creditor = (debt.getCreditor());
        creditorName = creditor.getName();
        amount = debt.getAmount();
    }
}
