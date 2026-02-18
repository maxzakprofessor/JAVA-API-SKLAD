package com.sklad.java_api.repository;

import com.sklad.java_api.model.GoodRest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRestRepository extends JpaRepository<GoodRest, Long> {
}
