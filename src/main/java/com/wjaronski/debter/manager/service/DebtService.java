package com.wjaronski.debter.manager.service;

import com.wjaronski.debter.manager.model.Debt;
import com.wjaronski.debter.manager.repository.DebtRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Wojciech Jaronski
 */

@Service
public class DebtService {
    private final DebtRepository debtRepository;

    public DebtService(DebtRepository debtRepository) {
        this.debtRepository = debtRepository;
    }

    public List<Debt> findAllByDebtor(String debtor) {
        return debtRepository.findAllByDebtor(debtor);
    }

    public List<Debt> findAllByCreditor(String creditor) {
        return debtRepository.findAllByCreditor(creditor);
    }

    public void addDebts(List<Debt> debtList) {
        debtList.forEach(this::addDebt);
    }

    @Transactional
    public void addDebt(final Debt debt) {
        if (debtExists(debt)) {
            Debt temp = getByCreditorAdnDebtor(debt.getCreditor(), debt.getDebtor());
            temp.updateAmount(debt.getAmount());
            debtRepository.save(temp);
        } else if (debtExists(Debt.getReversed(debt))) {
            Debt temp = getByCreditorAdnDebtor(debt.getDebtor(), debt.getCreditor());
            temp.updateAmount(debt.getAmount() * -1);
            debtRepository.save(temp);
        } else {
            debtRepository.save(debt);
        }
    }

    private Debt getByCreditorAdnDebtor(String creditor, String debtor) {
        return debtRepository.findByCreditorAndDebtor(creditor, debtor).orElse(null);
    }

    private boolean debtExists(Debt d1) {
        return debtRepository.findByCreditorAndDebtor(d1.getCreditor(), d1.getDebtor()).isPresent();
    }

    public List<Debt> findAll() {
        return debtRepository.findAll();
    }
}
