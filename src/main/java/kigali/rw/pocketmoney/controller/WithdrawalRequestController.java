package kigali.rw.pocketmoney.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kigali.rw.pocketmoney.domain.WithdrawalRequest;
import kigali.rw.pocketmoney.domain.WithdrawalStatus;
import kigali.rw.pocketmoney.service.WithdrawalRequestService;

@RestController 
@RequestMapping ("/api/withdrawal-requests")
public class WithdrawalRequestController {

    @Autowired 
    private  WithdrawalRequestService withdrawalRequestService;


    @PostMapping ("/save")
    public  ResponseEntity<?> save(@RequestBody  WithdrawalRequest withdrawalRequest){
        String result= withdrawalRequestService.save(withdrawalRequest);
        
        if (result.equals("Withdrawal request date cannot be in the past")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            
        }
        if (result.equals("Patron already handles a withdrawal on that date")) {
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
            
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping ("/all")
    public  ResponseEntity<?> getAll(){
        List<WithdrawalRequest> result= withdrawalRequestService.getAll();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getById(@PathVariable  UUID id){
        Optional<WithdrawalRequest> result = withdrawalRequestService.getById(id);

        if (result.isEmpty()) {
            return new ResponseEntity<>("The withdrawal request with that id does not exist", HttpStatus.NOT_FOUND);
            
        }
        return new ResponseEntity<>(result.get(), HttpStatus.OK);
    }

    @PutMapping ("/update/{id}")
    public  ResponseEntity<?> update(@PathVariable UUID id, @RequestBody  WithdrawalRequest withdrawalRequest){
        String result= withdrawalRequestService.update(id, withdrawalRequest);
        
        if (result.equals("The withdrawal request with that id does not exist")) {

            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable  UUID id){
        String result= withdrawalRequestService.delete(id);

        if (result.equals("The withdrawal request with that id does not exist")) {

            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
            
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping ("/by-status")
    public  ResponseEntity<?> getByStatus(@RequestParam WithdrawalStatus status){
        List<WithdrawalRequest> withdrawal= withdrawalRequestService.getByStatus(status);

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @GetMapping ("/between")
    public ResponseEntity<?> getBetween(@RequestParam  String start , @RequestParam String end){
        LocalDate startDate= LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        List<WithdrawalRequest> withdrawal= withdrawalRequestService.getBetween(startDate, endDate);
        return  new  ResponseEntity<>(withdrawal, HttpStatus.OK);
        
    }

    @PatchMapping ("/{id}/status")
    public  ResponseEntity<?> updateStatus(@PathVariable  UUID id, @RequestParam WithdrawalStatus status){
        String result= withdrawalRequestService.updateStatus(id, status);

        if (result.equals("The withdrawal request with that id does not exist")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        if (result.startsWith("Cannot change status")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
