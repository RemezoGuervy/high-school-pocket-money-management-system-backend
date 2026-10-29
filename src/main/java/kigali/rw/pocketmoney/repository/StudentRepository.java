package kigali.rw.pocketmoney.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kigali.rw.pocketmoney.domain.Student;

public interface StudentRepository extends  JpaRepository <Student, UUID> {

}
