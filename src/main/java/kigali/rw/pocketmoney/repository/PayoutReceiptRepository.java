package kigali.rw.pocketmoney.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kigali.rw.pocketmoney.domain.PayoutReceipt;

public interface PayoutReceiptRepository extends JpaRepository<PayoutReceipt, UUID> {



}
