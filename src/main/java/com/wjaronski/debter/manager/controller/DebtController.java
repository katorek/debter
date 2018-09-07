package com.wjaronski.debter.manager.controller;

import com.wjaronski.debter.manager.model.Debt;
import com.wjaronski.debter.manager.service.DebtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Wojciech Jaronski
 */

@RestController
@RequestMapping("/debt")
public class DebtController {

    private final DebtService debtService;

    public DebtController(DebtService debtService) {
        this.debtService = debtService;
    }

    @GetMapping
    public List<Debt> getAll() {
        return debtService.findAll();
    }
}
