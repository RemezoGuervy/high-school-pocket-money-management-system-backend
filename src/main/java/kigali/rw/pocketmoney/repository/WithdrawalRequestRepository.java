package kigali.rw.pocketmoney.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kigali.rw.pocketmoney.domain.Patron;
import kigali.rw.pocketmoney.domain.WithdrawalRequest;
import kigali.rw.pocketmoney.domain.WithdrawalStatus;

public interface WithdrawalRequestRepository extends JpaRepository <WithdrawalRequest, UUID> {

    List<WithdrawalRequest> findByStatusOrderByRequestDateAsc(WithdrawalStatus status);

    List<WithdrawalRequest> findByRequestDateBetweenOrderByRequestDateAsc(LocalDate start, LocalDate end);

    boolean existsByPatronAndRequestDateAndStatusNot(Patron patron, LocalDate requestDate, WithdrawalStatus status);

    @Query ("SELECT w.status, COUNT(w) FROM WithdrawalRequest w GROUP BY w.status")
    List<Object[]> countRequestPerStatus();

}
