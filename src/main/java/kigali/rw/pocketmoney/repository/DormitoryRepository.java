package kigali.rw.pocketmoney.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kigali.rw.pocketmoney.domain.Dormitory;

public interface DormitoryRepository extends JpaRepository<Dormitory, UUID> {

    @Query ("SELECT d FROM Dormitory d WHERE NOT EXISTS (SELECT p FROM Patron p WHERE p.dormitory=d)ORDER BY d.dormNumber")
    List<Dormitory> findFreeDormitories();

    Optional<Dormitory> findByDormNumber(int dormNumber);

}
