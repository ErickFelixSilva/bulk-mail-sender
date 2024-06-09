package com.erickfelix.mailsender.repository;

import com.erickfelix.mailsender.model.NonProfit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonProfitRepository extends JpaRepository<NonProfit, Long> {
}
