package com.wjaronski.debter.manager.controller;

import com.wjaronski.debter.manager.model.Bill;
import com.wjaronski.debter.manager.model.Debt;
import com.wjaronski.debter.manager.model.dto.DebtDto;
import com.wjaronski.debter.manager.service.BillService;
import com.wjaronski.debter.manager.service.DebtService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<DebtDto> getAll() {
        return debtService.findAll().stream().map(DebtDto::new).collect(Collectors.toList());
    }

    @GetMapping("/debts/{user}")
    public List<DebtDto> getDebtsForDebtor(@PathVariable String user,
                                           @RequestParam(name = "creditor", required = false, defaultValue = "false") boolean isCreditor,
                                           @RequestParam(name = "debtor", required = false, defaultValue = "false") boolean isDebtor) {
        List<Debt> debts;
        if (isCreditor) debts = debtService.findAllByCreditor(user);
        else if (isDebtor) debts = debtService.findAllByDebtor(user);
        else debts = debtService.findAllFor(user);

        return debts.stream().map(DebtDto::new).collect(Collectors.toList());
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
