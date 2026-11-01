package kigali.rw.pocketmoney.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.rw.pocketmoney.domain.WithdrawalStatus;
import kigali.rw.pocketmoney.repository.DormitoryRepository;
import kigali.rw.pocketmoney.repository.PatronRepository;
import kigali.rw.pocketmoney.repository.PayoutReceiptRepository;
import kigali.rw.pocketmoney.repository.StudentRepository;
import kigali.rw.pocketmoney.repository.WithdrawalRequestRepository;

@Service 
public class DashboardService {

    @Autowired 
    private StudentRepository studentRepository;
    
    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private DormitoryRepository dormitoryRepository;

    @Autowired 
    private WithdrawalRequestRepository withdrawalRequestRepository;

    @Autowired 
    private PayoutReceiptRepository payoutReceiptRepository;

    public Map<String, Long> getSummary(){

        Map<String, Long> summary= new LinkedHashMap<>();

        summary.put("students", studentRepository.count());
        summary.put("patrons", patronRepository.count());
        summary.put("freeDormitories", (long) dormitoryRepository.findFreeDormitories().size());
        summary.put("requestsToday", withdrawalRequestRepository.countByRequestDate(LocalDate.now()));
        summary.put("requested", withdrawalRequestRepository.countByStatus(WithdrawalStatus.REQUESTED));
        summary.put("payouts", payoutReceiptRepository.count());

        return summary;

    }

   
    }

