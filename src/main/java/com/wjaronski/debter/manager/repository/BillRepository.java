package com.wjaronski.debter.manager.repository;

import com.wjaronski.debter.manager.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Wojciech Jaronski
 */

public interface BillRepository extends JpaRepository<Bill, Long> {
}
