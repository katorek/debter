package com.wjaronski.debter.manager.converter;

import com.wjaronski.debter.manager.model.Debt;
import com.wjaronski.debter.manager.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;


/**
 * Created by Wojciech Jaronski
 */
public class ProductToDebtConverter implements Converter<Product, Debt> {
    @Nullable
    @Override
    public Debt convert(Product product) {
        Debt debt = new Debt();
        debt.setAmount(product.getPrice());
        debt.setCreditor(debt.getCreditor());
        debt.setDebtor(debt.getDebtor());
        return debt;
    }
}
