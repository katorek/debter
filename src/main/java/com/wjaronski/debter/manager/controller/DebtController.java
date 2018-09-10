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

    @GetMapping("/debts/{user}")
    public List<Debt> getDebtsForDebtor(@PathVariable String user,
                                        @RequestParam(name = "creditor", required = false, defaultValue = "false") boolean isCreditor,
                                        @RequestParam(name = "debtor", required = false, defaultValue = "false") boolean isDebtor) {
        if (isCreditor) return debtService.findAllByCreditor(user);
        if (isDebtor) return debtService.findAllByDebtor(user);
        return debtService.findAllFor(user);
    }

    @DeleteMapping("/debts")
    public void deleteAll() {
        debtService.deleteAll();
    }

    @PostMapping("/bill")
    public void addBill(@RequestBody @Valid Bill bill) {
        debtService.addDebts(billService.billToDebtList(bill));
    }
}
