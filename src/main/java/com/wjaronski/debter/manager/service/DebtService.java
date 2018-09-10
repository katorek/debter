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
        Debt debtToPersist;
        if ((debtToPersist = debtExists(debt)) != null) {
            debtToPersist.updateAmount(debt.getAmount());
            saveDebt(debtToPersist);
        } else if ((debtToPersist = debtExists(Debt.getReversed(debt))) != null) {
            debtToPersist.updateAmount(debt.getAmount());
            saveDebt(debtToPersist);
        } else {
            saveDebt(debt);
        }
    }

    private void saveDebt(Debt debt) {
        if (debt.getAmount() < 0.0) {
            Optional<Debt> tempDebt = findByCreditorAndDebtor(debt.getCreditor(), debt.getDebtor());
            if (tempDebt.isPresent()) {
                debt.setId(tempDebt.get().getId());
            }
            debt = Debt.getReversed(debt);
        }
        debt.setCreditor(userFromDb(debt.getCreditor()));
        debt.setDebtor(userFromDb(debt.getDebtor()));
        if (debt.getAmount().equals(0.0)) {
            debtRepository.deleteById(debt.getId());
        } else {
            debtRepository.save(debt);
        }
    }

    private User userFromDb(User user) {
        return userRepository.getByName(user.getName())
                .orElseGet(() -> userRepository.save(user));
    }

    private Optional<Debt> findByCreditorAndDebtor(User creditor, User debtor) {
        return debtRepository.findByCreditorAndDebtor(creditor.getName(), debtor.getName());
    }

    private Debt getByCreditorAndDebtor(User creditor, User debtor) {
        return findByCreditorAndDebtor(creditor, debtor).orElse(null);
    }

    private Debt debtExists(Debt d1) {

        return findByCreditorAndDebtor(d1.getCreditor(), d1.getDebtor()).orElse(null);
    }

    public List<Debt> findAll() {
        return debtRepository.findAll();
    }

    public List<Debt> findAllFor(String user) {
        return debtRepository.findAllDebtsForUser(user);
    }

    public void deleteAll() {
        debtRepository.deleteAll();
    }
}
