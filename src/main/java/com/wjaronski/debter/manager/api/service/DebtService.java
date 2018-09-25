package com.wjaronski.debter.manager.api.service;

import com.wjaronski.debter.manager.api.domain.Debt;
import com.wjaronski.debter.manager.api.domain.UserBean;
import com.wjaronski.debter.manager.api.repository.DebtRepository;
import com.wjaronski.debter.manager.api.repository.UserBeanRepository;
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
    private final UserBeanRepository userRepository;
    private final DebtOptimizer debtOptimizer;

    public DebtService(DebtRepository debtRepository, UserBeanRepository userRepository, DebtOptimizer debtOptimizer) {
        this.debtRepository = debtRepository;
        this.userRepository = userRepository;
        this.debtOptimizer = debtOptimizer;
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
            debtToPersist.updateAmount(debt.getAmount() * -1);
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
        optimizeDebts();
    }

    private UserBean userFromDb(UserBean userBean) {
        return userRepository.getByName(userBean.getName())
//                .orElseThrow(() -> new UsernameNotFoundException(userBean.getName()));
                .orElseGet(() -> userRepository.save(userBean));
    }

    private Optional<Debt> findByCreditorAndDebtor(UserBean creditor, UserBean debtor) {
        return debtRepository.findByCreditorAndDebtor(creditor.getName(), debtor.getName());
    }

    private Debt getByCreditorAndDebtor(UserBean creditor, UserBean debtor) {
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

    @Transactional
    public List<Debt> optimizeDebts() {
        List<Debt> debts = debtRepository.findAll();
        return debtOptimizer.optimize(debts);
    }
}
