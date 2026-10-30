package kigali.rw.pocketmoney.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.rw.pocketmoney.domain.WithdrawalRequest;
import kigali.rw.pocketmoney.domain.WithdrawalStatus;
import kigali.rw.pocketmoney.repository.WithdrawalRequestRepository;

@Service 
public class WithdrawalRequestService {

    @Autowired 
    private  WithdrawalRequestRepository withdrawalRequestRepository;

    public  String save(WithdrawalRequest withdrawalRequest){
        withdrawalRequestRepository.save(withdrawalRequest);

        return "Withdrawal request saved successfully";
    }

    public List<WithdrawalRequest> getAll(){

      return  withdrawalRequestRepository.findAll();
    }

    public  Optional<WithdrawalRequest> getById(UUID id){
        return  withdrawalRequestRepository.findById(id);
    }


    public  String update(UUID id, WithdrawalRequest withdrawalRequest){
        Optional<WithdrawalRequest>  result = withdrawalRequestRepository.findById(id);

        if (result.isEmpty()) {
            return  "The withdrawal request with that id does not exist";
            
        }

        WithdrawalRequest result2= result.get();

        result2.setReason(withdrawalRequest.getReason());
        result2.setAmount(withdrawalRequest.getAmount());

        withdrawalRequestRepository.save(result2);

        return  "Withdrawal request  updated successfully";

    }

    public String delete(UUID id){
        Optional<WithdrawalRequest> result = withdrawalRequestRepository.findById(id);

        if (result.isEmpty()) {
            return  "The withdrawal request with that id does not exist";
            
        }

        withdrawalRequestRepository.deleteById(id);
        return "Withdrawal request  deleted successfully";
    }

    public  List<WithdrawalRequest> getByStatus(WithdrawalStatus status){
        return withdrawalRequestRepository.findByStatusOrderByRequestDateAsc(status);
    }

}
