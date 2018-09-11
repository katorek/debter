package com.wjaronski.debter.manager.api.service;

import com.wjaronski.debter.manager.api.domain.Bill;
import com.wjaronski.debter.manager.api.domain.Debt;
import com.wjaronski.debter.manager.api.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojciech Jaronski
 */
@Service
public class BillService {
    private final ConverterService converterService;

    public BillService(ConverterService converterService) {
        this.converterService = converterService;
    }

    public List<Debt> billToDebtList(Bill bill) {

        List<Product> products = bill.getProducts();

        List<Debt> debts = new ArrayList<>();
        products.stream()
                .filter(Product::getEveryonePays)
                .map(product -> converterService.splitToDebtors(product, bill.getCreditor(), bill.getDebtors()))
                .forEach(debts::addAll);

        products.stream().filter(p -> !p.getEveryonePays()).map(p -> converterService.soloBillDebt(p, bill.getCreditor())).forEach(debts::add);

        return converterService.mergeDebts(debts);
    }

}
