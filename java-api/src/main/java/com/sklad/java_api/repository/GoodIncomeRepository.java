package com.sklad.java_api.repository;

import com.sklad.java_api.model.GoodIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodIncomeRepository extends JpaRepository<GoodIncome, Long> {
}
