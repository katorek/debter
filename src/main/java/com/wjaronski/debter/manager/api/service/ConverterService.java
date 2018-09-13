package com.wjaronski.debter.manager.api.service;

import com.wjaronski.debter.manager.api.domain.Debt;
import com.wjaronski.debter.manager.api.domain.Product;
import com.wjaronski.debter.manager.api.domain.UserBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wojta
 */

@Service
public class ConverterService {

    List<Debt> splitToDebtors(Product product, String creditorName, List<String> debtorsNames) {
        UserBean creditor = UserBean.getUserOf(creditorName);


        return debtorsNames.parallelStream()
                .map(debtor -> debtFor(product.getPrice(), creditor, UserBean.getUserOf(debtor), debtorsNames.size() + 1))
                .collect(Collectors.toList());
    }

    private Debt debtFor(double price, UserBean creditor, UserBean debtor, int divideBy) {
        Debt debt = new Debt();
        debt.setAmount(price / divideBy);
        debt.setCreditor(creditor);
        debt.setDebtor(debtor);
        return debt;
    }

    Debt soloDebt(Product product) {
        return debtFor(product.getPrice(), UserBean.getUserOf(product.getCreditor()), UserBean.getUserOf(product.getDebtor()), 1);
    }

    List<Debt> mergeDebts(List<Debt> debts) {
        List<Debt> newList = new ArrayList<>();
        for (int i = 0; i < debts.size(); i++) {
            for (int j = i + 1; debts.get(i) != null && j < debts.size(); j++) {
                if (debts.get(j) != null) {
                    if (mergableDebts(debts.get(i), debts.get(j))) {
                        Debt d1 = mergeDebts(debts.get(i), debts.get(j));
                        debts.set(i, d1);
                        debts.set(j, null);
                    }
                }
            }
            if (debts.get(i) != null) {
                newList.add(debts.get(i));
            }
        }
        return newList;
    }

    private Debt mergeDebts(Debt d1, Debt d2) {
        if (d1.getCreditor().equals(d2.getCreditor())) {
            d1.updateAmount(d2.getAmount());
        } else {
            d1.updateAmount(d2.getAmount() * -1);
        }
        return d1;
    }

    private boolean mergableDebts(Debt d1, Debt d2) {
        String d1C = d1.getCreditor().toString();
        String d1D = d1.getDebtor().toString();
        String d2C = d2.getCreditor().toString();
        String d2D = d2.getDebtor().toString();

        return (d1C.equals(d2C) && d1D.equals(d2D)) || (d1C.equals(d2D) && d1D.equals(d2C));
    }

    public Debt soloBillDebt(Product product, String creditor) {
        return debtFor(product.getPrice(), UserBean.getUserOf(creditor), UserBean.getUserOf(product.getDebtor()), 1);
    }
}
