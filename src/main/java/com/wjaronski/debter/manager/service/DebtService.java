package com.wjaronski.debter.manager.service;

import com.wjaronski.debter.manager.model.Debt;
import com.wjaronski.debter.manager.model.User;
import com.wjaronski.debter.manager.repository.DebtRepository;
import com.wjaronski.debter.manager.repository.UserRepository;
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
    private final UserRepository userRepository;

    public DebtService(DebtRepository debtRepository, UserRepository userRepository) {
        this.debtRepository = debtRepository;
        this.userRepository = userRepository;
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
            temp = getByCreditorAndDebtor(debt.getCreditor(), debt.getDebtor());
            temp.updateAmount(debt.getAmount());
        } else if (debtExists(Debt.getReversed(debt))) {
            temp = getByCreditorAndDebtor(debt.getDebtor(), debt.getCreditor());
            temp.updateAmount(debt.getAmount() * -1);
        } else {
            temp = debt;
        }
        save(temp);
    }

    private void save(Debt debt) {
        if (debt.getAmount() < 0.0) {
            Optional<Debt> tempDebt = findByCreditorAndDebtor(debt.getCreditor(), debt.getDebtor());
            if (tempDebt.isPresent()) {
                debt.setId(tempDebt.get().getId());
            }
            debt = Debt.getReversed(debt);
        }
        debtRepository.save(debt);
    }

    private Optional<Debt> findByCreditorAndDebtor(User creditor, User debtor) {
        Optional<User> optCred = userRepository.getByName(creditor.getName());
        Optional<User> optDebt = userRepository.getByName(debtor.getName());

        if (!optCred.isPresent()) creditor = userRepository.save(creditor);
        if (!optDebt.isPresent()) debtor = userRepository.save(debtor);


        return debtRepository.findByCreditorAndDebtor(creditor, debtor);
    }

    private Debt getByCreditorAndDebtor(User creditor, User debtor) {
        return findByCreditorAndDebtor(creditor, debtor).orElse(null);
    }

    private boolean debtExists(Debt d1) {
//        String creditor = d1.getCreditor().toString();
//        String debtor = d1.getDebtor().toString();

        return findByCreditorAndDebtor(d1.getCreditor(), d1.getDebtor()).isPresent();
    }

    public List<Debt> findAll() {
        return debtRepository.findAll();
    }

    public List<Debt> findAllFor(String user) {
        return debtRepository.findAllByCreditorOrDebtor(user, user);
    }

    public void deleteAll() {
        debtRepository.deleteAll();
    }
}
