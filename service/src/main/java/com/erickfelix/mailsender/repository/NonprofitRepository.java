package com.erickfelix.mailsender.repository;

import com.erickfelix.mailsender.model.Nonprofit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NonprofitRepository extends JpaRepository<Nonprofit, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Nonprofit n SET n.emailSent = true WHERE n.id IN :ids")
    void markAsRecentlySent(List<Long> ids);
}
