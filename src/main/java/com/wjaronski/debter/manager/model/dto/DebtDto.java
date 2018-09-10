package com.wjaronski.debter.manager.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wjaronski.debter.manager.model.Debt;
import lombok.Data;

/**
 * Created by wojta
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DebtDto {
    private UserDto debtor;
    private UserDto creditor;
    private Double amount;

    public DebtDto(Debt debt) {
        debtor = new UserDto(debt.getDebtor());
        creditor = new UserDto(debt.getCreditor());
        amount = debt.getAmount();
    }
}
