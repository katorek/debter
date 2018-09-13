//package com.wjaronski.debter.manager;
//
//import com.wjaronski.debter.manager.api.model.Debt;
//import com.wjaronski.debter.manager.api.model.Product;
//import com.wjaronski.debter.manager.api.model.UserBean;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * Created by wojta
// */
//
//public class ConverterServiceTest {
//
//    private static final double AMOUNT = 1.2;
//    private ConverterService converterService;
//
//    @Before
//    public void init() {
//        converterService = new ConverterService();
//    }
//
//    @Test
//    public void splitToDebtors_oneDebtor() {
////        Bill bill = new Bill();
//
//        Product product = new Product();
//        product.setPrice(0.0);
//
//        assertThat(converterService.splitToDebtors(product, ("Creditor"), Collections.singletonList(("Debtor")))).hasSize(1);
//    }
//
//    @Test
//    public void splitToDebtors_multipleDebtors() {
//        Product p1 = new Product();
//        p1.setPrice(100.0);
//
//        int numberOfDebtors = 4;
//
//        List<String> debtors = IntStream.range(0, numberOfDebtors).mapToObj(i -> ("" + i)).collect(Collectors.toList());
//
//        List<Debt> splittedDebts = converterService.splitToDebtors(p1, ("Creditor"), debtors);
//
//        assertThat(splittedDebts).hasSize(4).allMatch(debt -> debt.getAmount().equals(p1.getPrice() / (numberOfDebtors + 1)));
//    }
//
//    private Debt getExactlyTheSameDebt() {
//        Debt debt = new Debt();
//        debt.setCreditor(UserBean.getUserOf("Creditor"));
//        debt.setDebtor(UserBean.getUserOf("Debtor"));
//        debt.setAmount(0.0);
//        return debt;
//    }
//
//    private Debt getUniqueDebt() {
//        Debt debt = new Debt();
//        debt.setDebtor(UserBean.getUserOf(UUID.randomUUID().toString()));
//        debt.setCreditor(UserBean.getUserOf(UUID.randomUUID().toString()));
//        debt.setAmount(0.0);
//        return debt;
//    }
//
//    @Test
//    public void mergeDebts_noMergableDebts_returnOrigin() {
//        List<Debt> debts = IntStream.range(0, 10).mapToObj(i -> getUniqueDebt()).collect(Collectors.toList());
//
//        assertThat(debts).hasSameElementsAs(converterService.mergeDebts(debts));
//    }
//
//    @Test
//    public void mergeDebts_allMergable_returnsSingletonList() {
//        List<Debt> debts = IntStream.range(0, 10).mapToObj(i -> getExactlyTheSameDebt()).collect(Collectors.toList());
//
//        List<Debt> mergeDebts = converterService.mergeDebts(debts);
////        assertThat(debts).containsOnly(getExactlyTheSameDebt());
//        assertThat(mergeDebts).contains(debts.get(0)).hasSize(1);
//    }
//
//    @Test
//    public void mergeDebts_halfMergable_returnMergedList() {
//        List<Debt> debts = IntStream.range(0, 5).mapToObj(i -> getUniqueDebt()).collect(Collectors.toList());
//        debts.addAll(IntStream.range(0, 5).mapToObj(i -> getExactlyTheSameDebt()).collect(Collectors.toList()));
//
//        List<Debt> mergedDebts = converterService.mergeDebts(debts);
//
//        assertThat(mergedDebts).hasSize(6);
//    }
//
//
//}