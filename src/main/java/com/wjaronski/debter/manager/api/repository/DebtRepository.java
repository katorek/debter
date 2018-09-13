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
    @Query("select d from UserBean u1, UserBean u2, Debt d where u1.login=:creditor and u2.login=:debtor and d.creditor = u1.id and d.debtor=u2.id")
    Optional<Debt> findByCreditorAndDebtor(@Param("creditor") String creditor, @Param("debtor") String debtor);

    @Query("select d from UserBean u, Debt d where u.login=:user and (d.creditor = u.id or d.debtor = u.id)")
    List<Debt> findAllDebtsForUser(@Param("user") String user);

    @Query("select d from UserBean u, Debt d where u.login=:creditor and d.creditor = u.id")
    List<Debt> findAllByCreditor(@Param("creditor") String creditor);

    @Query("select d from UserBean u, Debt d where u.login=:debtor and d.debtor = u.id")
    List<Debt> findAllByDebtor(@Param("debtor") String debtor);

    void deleteByCreditorAndDebtor(String creditor, String debtor);
}
