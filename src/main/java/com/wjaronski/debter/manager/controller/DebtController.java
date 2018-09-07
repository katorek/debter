package com.wjaronski.debter.manager.controller;

import com.wjaronski.debter.manager.model.Bill;
import com.wjaronski.debter.manager.model.Debt;
import com.wjaronski.debter.manager.service.BillService;
import com.wjaronski.debter.manager.service.DebtService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Wojciech Jaronski
 */

@RestController
public class DebtController {

    private final DebtService debtService;
    private final BillService billService;

    public DebtController(DebtService debtService, BillService billService) {
        this.debtService = debtService;
        this.billService = billService;
    }

    @GetMapping("/debts")
    public List<Debt> getAll() {
        return debtService.findAll();
    }

    @GetMapping("/debtor/{debtorName}")
    public List<Debt> getDebtsForDebtor(@PathVariable String debtorName) {
        return debtService.findAllByDebtor(debtorName);
    }

    @GetMapping("/creditor/{creditorName}")
    public List<Debt> detDebtsForCreditor(@PathVariable String creditorName) {
        return debtService.findAllByCreditor(creditorName);
    }

    @PostMapping("/bill")
    public void addBill(@RequestBody @Valid Bill bill) {
        debtService.addDebts(billService.billToDebtList(bill));
    }
}
