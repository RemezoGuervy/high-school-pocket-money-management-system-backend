package kigali.rw.pocketmoney.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kigali.rw.pocketmoney.domain.WithdrawalRequest;

public interface WithdrawalRequestRepository extends JpaRepository <WithdrawalRequest, UUID> {

}
