package com.wjaronski.debter.manager.repository;

import com.wjaronski.debter.manager.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Wojciech Jaronski
 */

public interface DebtRepository extends JpaRepository<Debt, Long> {

    Optional<Debt> findByCreditorAndDebtor(String creditor, String debtor);

    Set<Debt> getAllByDebtor(String debtor);

    Set<Debt> getAllByCreditor(String creditor);

}
