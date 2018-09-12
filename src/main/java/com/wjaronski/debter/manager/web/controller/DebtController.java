package com.wjaronski.debter.manager.web.controller;

import com.wjaronski.debter.manager.api.domain.Bill;
import com.wjaronski.debter.manager.api.domain.Debt;
import com.wjaronski.debter.manager.api.domain.dto.DebtDto;
import com.wjaronski.debter.manager.api.service.BillService;
import com.wjaronski.debter.manager.api.service.DebtService;
import com.wjaronski.debter.manager.web.controller.dto.SimpleDebt;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Wojciech Jaronski
 */

@RestController
@RequestMapping("/debts")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FACEBOOK_USER')")
public class DebtController {

    private final DebtService debtService;
    private final BillService billService;

    public DebtController(DebtService debtService, BillService billService) {
        this.debtService = debtService;
        this.billService = billService;
    }

    @GetMapping
    public List<DebtDto> getAll() {
        return debtService.findAll().stream().map(DebtDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{user}")
    public List<DebtDto> getDebtsForDebtor(@PathVariable String user,
                                           @RequestParam(name = "creditor", required = false, defaultValue = "false") boolean isCreditor,
                                           @RequestParam(name = "debtor", required = false, defaultValue = "false") boolean isDebtor) {
        List<Debt> debts;
        if (isCreditor && isDebtor) return null;
        else if (isCreditor) debts = debtService.findAllByCreditor(user);
        else if (isDebtor) debts = debtService.findAllByDebtor(user);
        else debts = debtService.findAllFor(user);

        return debts.stream().map(DebtDto::new).collect(Collectors.toList());
    }

    @DeleteMapping
    public void deleteAll() {
        debtService.deleteAll();
    }

    @PostMapping("/addBill")
    public void addBill(@RequestBody @Valid Bill bill) {
        debtService.addDebts(billService.billToDebtList(bill));
    }

    @PostMapping
    public void addDebt(@RequestBody @Valid SimpleDebt simpleDebt) {
        debtService.addDebt(simpleDebt.toDebt());
    }

    @GetMapping("/optimize")
    public void optimizeDebts() {
        debtService.optimizeDebts();
    }

}
