package kigali.rw.pocketmoney.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kigali.rw.pocketmoney.domain.Duty;

public interface DutyRepository extends JpaRepository<Duty, UUID> {

}
