package kigali.rw.pocketmoney.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kigali.rw.pocketmoney.domain.Duty;

public interface DutyRepository extends JpaRepository<Duty, UUID> {

    @Query ("SELECT d FROM Duty d WHERE d.patrons IS EMPTY")
    List<Duty> findUnusedDuties();

}
