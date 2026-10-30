package kigali.rw.pocketmoney.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kigali.rw.pocketmoney.domain.Student;

public interface StudentRepository extends  JpaRepository <Student, UUID> {
    
    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastname);

}
