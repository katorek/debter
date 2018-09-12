//package com.wjaronski.debter.manager;
//
//import com.wjaronski.debter.manager.api.model.Bill;
//import com.wjaronski.debter.manager.api.model.Debt;
//import com.wjaronski.debter.manager.api.model.Product;
//import org.junit.Before;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * Created by wojta
// */
//
//public class BillServiceTest {
//    private static final double SHARABLE_PRODUCT_PRICE = 10.0;
//
//    private BillService billService;
//    /*@Mock
//    private UserService userService;*/
//
//    @Before
//    public void before() {
//        MockitoAnnotations.initMocks(this);
//        billService = new BillService(new ConverterService()/*, userService*/);
//        /*when(userService.save(any(User.class))).thenReturn(null);
//        when(userService.save(anyString())).thenReturn(null);*/
//    }
//
//    //    @Test
//    public void billToDebtList() {
//        String creditor = "creditor";
//        int howManyDebtors = 3;
//        int howManyProducts = 11;
//
//        List<String> debtors = IntStream.range(0, howManyDebtors).mapToObj(i -> ("debtor_" + i)).collect(Collectors.toList());
//        List<Product> products = IntStream.range(0, howManyProducts).mapToObj(this::sharableProduct).collect(Collectors.toList());
//
//        Bill bill = new Bill();
//        bill.setDebtors(debtors);
//        bill.setCreditor(creditor);
//        bill.setProducts(products);
//
//        List<Debt> debts = billService.billToDebtList(bill);
//
//        assertThat(debts).hasSize(howManyDebtors);
//        assertThat(debts).allMatch(debt -> debt.getAmount().equals(SHARABLE_PRODUCT_PRICE * howManyProducts / (howManyDebtors + 1)));
//        assertThat(debts).allMatch(debt -> debt.getCreditor().equals(creditor));
//
//    }
//
//    private Product sharableProduct(int i) {
//        Product p = new Product();
//        p.setPrice(SHARABLE_PRODUCT_PRICE);
//        p.setDebtor(("debtor_" + i));
//        p.setEveryonePays(true);
//        return p;
//    }
//}