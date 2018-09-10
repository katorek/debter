package com.wjaronski.debter.manager.service;

import com.wjaronski.debter.manager.model.Debt;
import com.wjaronski.debter.manager.repository.DebtRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        Debt temp;
        if (debtExists(debt)) {
            temp = getByCreditorAdnDebtor(debt.getCreditor(), debt.getDebtor());
            temp.updateAmount(debt.getAmount());
        } else if (debtExists(Debt.getReversed(debt))) {
            temp = getByCreditorAdnDebtor(debt.getDebtor(), debt.getCreditor());
            temp.updateAmount(debt.getAmount() * -1);
        } else {
            temp = debt;
        }
        save(temp);
    }

    private void save(Debt debt) {
        if (debt.getAmount() < 0.0) {
            //todo remove
            Optional<Debt> tempDebt = debtRepository.findByCreditorAndDebtor(debt.getCreditor(), debt.getDebtor());
            if (tempDebt.isPresent()) {
                debt.setId(tempDebt.get().getId());
            }
            debt = Debt.getReversed(debt);
            debt.reverseAmount();
        }
        debtRepository.save(debt);
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

    public List<Debt> findAllFor(String user) {
        return debtRepository.findAllByCreditorOrDebtor(user, user);
    }
}
