package com.wjaronski.debter.manager.web.controller;

import com.wjaronski.debter.manager.api.domain.Bill;
import com.wjaronski.debter.manager.api.domain.Debt;
import com.wjaronski.debter.manager.api.domain.dto.DebtDto;
import com.wjaronski.debter.manager.api.facebook.ProfileInfoService;
import com.wjaronski.debter.manager.api.facebook.dto.Profile;
import com.wjaronski.debter.manager.api.service.BillService;
import com.wjaronski.debter.manager.api.service.DebtService;
import com.wjaronski.debter.manager.web.controller.dto.SimpleDebt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Wojciech Jaronski
 */
@Slf4j
@RestController
@RequestMapping("/debts")
@PreAuthorize("isAuthenticated()")
public class DebtController {

    private final DebtService debtService;
    private final BillService billService;

    private final ProfileInfoService profileInfoService;

    public DebtController(DebtService debtService, BillService billService, ProfileInfoService profileInfoService) {
        this.debtService = debtService;
        this.billService = billService;
        this.profileInfoService = profileInfoService;
    }

    @GetMapping("/all")
    @PreAuthorize("isMember('administrators')")
    public List<DebtDto> getAll() {
        Profile p = profileInfoService.getProfile();
        log.info("Profile: {}", p);

        return debtService.findAll().stream().map(DebtDto::new).collect(Collectors.toList());
    }

    @GetMapping
    public List<DebtDto> getMyDebts() {
        Profile currentUser = profileInfoService.getProfile();
        return debtService.findAllFor(currentUser.getId())
                .stream()
                .map(DebtDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    @PreAuthorize("isMember('administrators')")
    public List<DebtDto> getDebtsForDebtor(@PathVariable String userId,
                                           @RequestParam(name = "creditor", required = false, defaultValue = "false") boolean isCreditor,
                                           @RequestParam(name = "debtor", required = false, defaultValue = "false") boolean isDebtor) {
        List<Debt> debts;
        if (isCreditor && isDebtor) return null;
        else if (isCreditor) debts = debtService.findAllByCreditor(userId);
        else if (isDebtor) debts = debtService.findAllByDebtor(userId);
        else debts = debtService.findAllFor(userId);

        return debts.stream()
                .map(DebtDto::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping
    @PreAuthorize("isMember('administrators')")
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
    @PreAuthorize("isMember('administrators')")
    public List<Debt> optimizeDebts() {
        return debtService.optimizeDebts();
    }

}
