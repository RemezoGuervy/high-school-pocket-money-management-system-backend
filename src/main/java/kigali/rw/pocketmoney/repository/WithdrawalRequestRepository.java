package kigali.rw.pocketmoney.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import kigali.rw.pocketmoney.domain.Patron;
import kigali.rw.pocketmoney.domain.WithdrawalRequest;
import kigali.rw.pocketmoney.domain.WithdrawalStatus;

public interface WithdrawalRequestRepository extends JpaRepository <WithdrawalRequest, UUID> {

    List<WithdrawalRequest> findByStatusOrderByRequestDateAsc(WithdrawalStatus status);

    List<WithdrawalRequest> findByRequestDateBetweenOrderByRequestDateAsc(LocalDate start, LocalDate end);

    boolean existsByPatronAndRequestDateAndStatusNot(Patron patron, LocalDate requestDate, WithdrawalStatus status);

    @Query ("SELECT w.status, COUNT(w) FROM WithdrawalRequest w GROUP BY w.status")
    List<Object[]> countRequestPerStatus();

    @Modifying 
    @Transactional 
    @Query ("UPDATE WithdrawalRequest w SET w.status = :newStatus WHERE w.patron.id = :patronId AND w.requestDate = :day AND w.status <> :keepStatus")
    int cancelPatronDay(@Param("patronId") UUID patronid, @Param("day") LocalDate day, @Param("newStatus") WithdrawalStatus newStatus, @Param("keepStatus") WithdrawalStatus keepStatus);

}
