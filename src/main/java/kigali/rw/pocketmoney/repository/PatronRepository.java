package kigali.rw.pocketmoney.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kigali.rw.pocketmoney.domain.Patron;

public interface PatronRepository extends JpaRepository<Patron, UUID>{

}
