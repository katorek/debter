package com.wjaronski.debter.manager.service;

import com.wjaronski.debter.manager.model.Bill;
import com.wjaronski.debter.manager.model.Debt;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Wojciech Jaronski
 */
@Service
public class BillService {
    private final ConversionService conversionService;

    public BillService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public List<Debt> billToDebtList(Bill bill) {
        return bill.getProducts()
                .stream()
                .map(product -> conversionService.convert(product, Debt.class))
                .collect(Collectors.toList());
    }
}
