package com.wjaronski.debter.manager.repository;

import com.wjaronski.debter.manager.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Wojciech Jaronski
 */

public interface DebtRepository extends JpaRepository<Debt, Long> {

    Optional<Debt> findByCreditorAndDebtor(String creditor, String debtor);

    List<Debt> findAllByCreditor(String creditor);

    List<Debt> findAllByDebtor(String debtor);

    List<Debt> findAllByCreditorOrDebtor(String creditor, String debtor);

    void deleteByCreditorAndDebtor(String creditor, String debtor);
}
