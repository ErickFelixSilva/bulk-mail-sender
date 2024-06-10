package com.erickfelix.mailsender.repository;

import com.erickfelix.mailsender.model.Nonprofit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonprofitRepository extends JpaRepository<Nonprofit, Long> {
}
