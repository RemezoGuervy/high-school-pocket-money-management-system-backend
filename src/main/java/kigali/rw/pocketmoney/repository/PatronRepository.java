package kigali.rw.pocketmoney.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kigali.rw.pocketmoney.domain.Dormitory;
import kigali.rw.pocketmoney.domain.Patron;

public interface PatronRepository extends JpaRepository<Patron, UUID>{

    @Query ("SELECT p FROM Patron p JOIN p.duties d WHERE LOWER(d.name) LIKE  LOWER(CONCAT ('%', :name , '%'))")
    List<Patron> searchByDutyName(@Param("name") String name);

    boolean existsByDormitory(Dormitory dormitory);

}
