package com.wjaronski.debter.manager.api.repository;

import com.wjaronski.debter.manager.api.domain.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by Wojciech Jaronski
 */

public interface DebtRepository extends JpaRepository<Debt, Long> {
    @Query("select d from User u1, User u2, Debt d where u1.name=:creditor and u2.name=:debtor and d.creditor = u1.id and d.debtor=u2.id")
    Optional<Debt> findByCreditorAndDebtor(@Param("creditor") String creditor, @Param("debtor") String debtor);

    @Query("select d from User u, Debt d where u.name=:user and (d.creditor = u.id or d.debtor = u.id)")
    List<Debt> findAllDebtsForUser(@Param("user") String user);

    @Query("select d from User u, Debt d where u.name=:creditor and d.creditor = u.id")
    List<Debt> findAllByCreditor(@Param("creditor") String creditor);

    @Query("select d from User u, Debt d where u.name=:debtor and d.debtor = u.id")
    List<Debt> findAllByDebtor(@Param("debtor") String debtor);

    void deleteByCreditorAndDebtor(String creditor, String debtor);
}
