package kigali.rw.pocketmoney.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kigali.rw.pocketmoney.domain.Student;

public interface StudentRepository extends  JpaRepository <Student, UUID> {

    @Query ("SELECT DISTINCT w.student FROM WithdrawalRequest w WHERE w.patron.id = :patronId ")
    List<Student> findStudentsOfPatron(@Param ("patronId") UUID patronId);

    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastname);

}
