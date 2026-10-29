package kigali.rw.pocketmoney.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "withdrawal_request")
public class WithdrawalRequest {

    @Id 
    @GeneratedValue (strategy =  GenerationType.UUID)
    private UUID id;

    @Column
    private  LocalDate requestDate;
    

    @Enumerated (EnumType.STRING)
    @Column 
    private WithdrawalStatus status;

    @Column 
    private  double amount;

    @Column 
    private  String reason;

    @ManyToOne
    @JoinColumn (name = "student_id")
    private Student student;

    @ManyToOne 
    @JoinColumn (name = "patron_id")
    private  Patron patron;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public WithdrawalStatus getStatus() {
        return status;
    }

    public void setStatus(WithdrawalStatus status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }


    
}
