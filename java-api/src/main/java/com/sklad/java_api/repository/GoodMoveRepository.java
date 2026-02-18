package com.sklad.java_api.repository;

import com.sklad.java_api.model.GoodMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodMoveRepository extends JpaRepository<GoodMove, Long> {
}
