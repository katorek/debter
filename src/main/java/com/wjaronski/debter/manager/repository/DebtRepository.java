package com.wjaronski.debter.manager.repository;

import com.wjaronski.debter.manager.model.Debt;
import com.wjaronski.debter.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Wojciech Jaronski
 */

public interface DebtRepository extends JpaRepository<Debt, Long> {

    //    Optional<Debt> findByCreditorAndDebtor(Long creditorId, Long debtorId);
//    Optional<Debt> findByCreditorAndDebtor(String creditor, String debtor);
    Optional<Debt> findByCreditorAndDebtor(User creditor, User debtor);

//    Debt findByDebtorI

    List<Debt> findAllByCreditor(String creditor);

    List<Debt> findAllByDebtor(String debtor);

    List<Debt> findAllByCreditorOrDebtor(String creditor, String debtor);

    void deleteByCreditorAndDebtor(String creditor, String debtor);
}
