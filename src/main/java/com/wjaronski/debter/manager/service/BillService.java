package com.wjaronski.debter.manager.service;

import com.wjaronski.debter.manager.model.Bill;
import com.wjaronski.debter.manager.model.Debt;
import com.wjaronski.debter.manager.model.Product;
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

//        List<Debt> debts = products.stream().filter(Product::getEveryonePays).map(product -> converterService.splitToDebtors(product,bill.getCreditor(),bill.getDebtors()));
        List<Debt> debts = new ArrayList<>();
        products.stream()
                .filter(Product::getEveryonePays)
                .map(product -> converterService.splitToDebtors(product, bill.getCreditor(), bill.getDebtors()))
                .forEach(debts::addAll);

        products.stream().filter(p -> !p.getEveryonePays()).map(p -> converterService.soloBillDebt(p, bill.getCreditor())).forEach(debts::add);

        return converterService.mergeDebts(debts);
    }
}
