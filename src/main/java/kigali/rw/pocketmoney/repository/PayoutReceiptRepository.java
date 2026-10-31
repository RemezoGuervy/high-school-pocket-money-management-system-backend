package kigali.rw.pocketmoney.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kigali.rw.pocketmoney.domain.PayoutReceipt;
import java.util.List;
import java.time.LocalDate;


public interface PayoutReceiptRepository extends JpaRepository<PayoutReceipt, UUID> {


    @Query ("SELECT p FROM PayoutReceipt p WHERE p.withdrawalRequest.student.studentNumber = :studentNumber ORDER BY p.paidDate Desc")
    List<PayoutReceipt> searchReceiptsByStudentNumber(@Param("studentNumber") String studentNumber );



}
