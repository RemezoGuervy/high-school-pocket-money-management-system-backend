package kigali.rw.pocketmoney.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kigali.rw.pocketmoney.domain.PayoutReceipt;
import kigali.rw.pocketmoney.service.PayoutReceiptService;

@RestController 
@RequestMapping ("/api/payout-receipts")
public class PayoutReceiptController {

    @Autowired 
    private PayoutReceiptService payoutReceiptService;

    @PostMapping ("/save")
    public  ResponseEntity<?> save(@RequestBody PayoutReceipt  payoutReceipt, @RequestParam  UUID withdrawalRequestId  ){
        String result= payoutReceiptService.save(payoutReceipt, withdrawalRequestId);

        if (result.equals("The withdrawal request with that id does not exist")) {

            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            
        }
        if (result.equals("Payout receipts are only for DISBURSED withdrawal requests")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @GetMapping ("/student/{studentNumber}")
    public  ResponseEntity<?> getByStudentNumber(@PathVariable String studentNumber){
        List<PayoutReceipt> receipt= payoutReceiptService.getByStudentNumber(studentNumber);
        return  new  ResponseEntity<>(receipt, HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public  ResponseEntity<?> delete(@PathVariable UUID id){
        String result= payoutReceiptService.delete(id);

        if (result.equals("The payout receipt with that id does not exist")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
