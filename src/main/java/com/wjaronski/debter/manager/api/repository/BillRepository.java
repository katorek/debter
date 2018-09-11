package com.wjaronski.debter.manager.api.repository;

import com.wjaronski.debter.manager.api.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Wojciech Jaronski
 */

public interface BillRepository extends JpaRepository<Bill, Long> {
}
