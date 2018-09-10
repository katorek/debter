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
//    private final UserService userService;

    public BillService(ConverterService converterService/*, UserService userService*/) {
        this.converterService = converterService;
        /*this.userService = userService;*/
    }

    public List<Debt> billToDebtList(Bill bill) {

        List<Product> products = bill.getProducts();

//        saveUsersFromBill(bill);

//        List<Debt> debts = products.stream().filter(Product::getEveryonePays).map(product -> converterService.splitToDebtors(product,bill.getCreditor(),bill.getDebtors()));
        List<Debt> debts = new ArrayList<>();
        products.stream()
                .filter(Product::getEveryonePays)
                .map(product -> converterService.splitToDebtors(product, bill.getCreditor(), bill.getDebtors()))
                .forEach(debts::addAll);

        products.stream().filter(p -> !p.getEveryonePays()).map(p -> converterService.soloBillDebt(p, bill.getCreditor())).forEach(debts::add);

        return converterService.mergeDebts(debts);

        /*HashSet<User> users = new HashSet<>();
        debts.stream().forEach(debt -> {
            users.add(debt.getCreditor());
            users.add(debt.getDebtor());
        });*/

        /*users.forEach(userService::save);*/

        /*return debts;*/
    }

    /*private void saveUsersFromBill(Bill bill) {
        userService.save(bill.getCreditor());
        bill.getDebtorsUsers().parallelStream().forEach(userService::save);
    }*/
}
