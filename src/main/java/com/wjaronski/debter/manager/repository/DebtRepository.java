package com.wjaronski.debter.manager.repository;

import com.wjaronski.debter.manager.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by Wojciech Jaronski
 */

public interface DebtRepository extends JpaRepository<Debt, Long> {
    /**
     * //     * Finds a person by using the last name as a search criteria.
     * //     * @param lastName
     * //     * @return  A list of persons whose last name is an exact match with the given last name.
     * //     *          If no persons is found, this method returns an empty list.
     * //
     */
//    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
//    public List<Person> find(@Param("lastName") String lastName);


    //    Optional<Debt> findByCreditorAndDebtor(Long creditorId, Long debtorId);
//    Optional<Debt> findByCreditorAndDebtor(String creditor, String debtor);

//    @Query("select debt from Debt debt, where debt.creditor = :creditor and debt.debtor = :debtor")
    @Query("select d from User u1, User u2, Debt d where u1.name=:creditor and u2.name=:debtor and d.creditor = u1.id and d.debtor=u2.id")
    Optional<Debt> findByCreditorAndDebtor(@Param("creditor") String creditor, @Param("debtor") String debtor);

//    Optional<Debt> findByCreditorAndDebtor(@Param("creditor") Long creditor,@Param("debtor") Long debtor);
//    Optional<Debt> findByCreditorAndDebtor(User creditor, User debtor);

//    Debt findByDebtorI

    List<Debt> findAllByCreditor(String creditor);

    List<Debt> findAllByDebtor(String debtor);

    List<Debt> findAllByCreditorOrDebtor(String creditor, String debtor);

    void deleteByCreditorAndDebtor(String creditor, String debtor);
}
