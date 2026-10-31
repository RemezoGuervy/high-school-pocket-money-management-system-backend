package kigali.rw.pocketmoney.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.rw.pocketmoney.domain.PayoutReceipt;
import kigali.rw.pocketmoney.domain.WithdrawalRequest;
import kigali.rw.pocketmoney.domain.WithdrawalStatus;
import kigali.rw.pocketmoney.repository.PayoutReceiptRepository;
import kigali.rw.pocketmoney.repository.WithdrawalRequestRepository;

@Service 
public class PayoutReceiptService {
    
    @Autowired 
    private PayoutReceiptRepository  payoutReceiptRepository;

    @Autowired 
    private  WithdrawalRequestRepository withdrawalRequestRepository;


    public String save(PayoutReceipt payoutReceipt, UUID  withdrawalRequestId){

        Optional<WithdrawalRequest> result= withdrawalRequestRepository.findById(withdrawalRequestId);
        if (result.isEmpty()) {

            return "The withdrawal request with that id does not exist";
            
        }

        WithdrawalRequest withdrawalRequest = result.get();

        if (withdrawalRequest.getStatus() != WithdrawalStatus.DISBURSED) {

            return "Payout receipts are only for DISBURSED withdrawal requests";
            
        }

        payoutReceipt.setPaidDate(LocalDate.now());
        payoutReceipt.setWithdrawalRequest(withdrawalRequest);

        payoutReceiptRepository.save(payoutReceipt);

        return "Payout receipt saved successfully";
    }

}
