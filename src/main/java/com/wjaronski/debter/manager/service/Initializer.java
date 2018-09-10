//package com.wjaronski.debter.manager.service;
//
//import com.wjaronski.debter.manager.model.Bill;
//import com.wjaronski.debter.manager.model.Product;
//
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
///**
// * Created by Wojciech Jaronski
// */
//
////@Component
//public class Initializer {
//    private static final double DEFAULT_PRICE = 1.5;
//
//    private final DebtService debtService;
//    private final BillService billService;
//
//    public Initializer(DebtService debtService, BillService billService) {
//        this.debtService = debtService;
//        this.billService = billService;
//        initTestData();
//    }
//
//    private void initTestData() {
//        String debtor = "deb";
//        String creditor = "cred";
//
//
//        Bill bill = Bill.getBillOfProducts(IntStream.range(0, 5)
//                .mapToObj(it -> getRandomForCreditorAndDebtor(creditor, debtor))
//                .collect(Collectors.toList()));
//
//        billService.billToDebtList(bill).forEach(debtService::addDebt);
//    }
//
//    private Product getRandomForCreditorAndDebtor(User creditor, User debtor) {
//        Product product = new Product();
//        product.setCreditor(creditor);
//        product.setDebtor(debtor);
//        product.setPrice(DEFAULT_PRICE);
//        return product;
//    }
//}
