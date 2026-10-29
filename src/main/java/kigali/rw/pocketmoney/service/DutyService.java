package kigali.rw.pocketmoney.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.rw.pocketmoney.domain.Duty;
import kigali.rw.pocketmoney.repository.DutyRepository;

@Service 
public class DutyService {

    @Autowired 
    private  DutyRepository dutyRepository;

    public  String save(Duty duty){
        dutyRepository.save(duty);

        return "Duty saved successfully";
    }

    public  List<Duty> findAll(){
        return  dutyRepository.findAll();
    }

    public Optional<Duty> findById(UUID id){
        return dutyRepository.findById(id);
    }

    public  String update(UUID id,  Duty duty){

        Optional<Duty> dutServe= dutyRepository.findById(id);

        if (dutServe.isEmpty()) {

            return "The duty with that id does not exist";
            
        }

        Duty exisDuty= dutServe.get();

        exisDuty.setName(duty.getName());
        

        dutyRepository.save(exisDuty);

        return "Duty updated successfully";
    }

    public  String delete(UUID id){
        Optional<Duty> dutyDel= dutyRepository.findById(id);

        if (dutyDel.isEmpty()) {

            return "The duty with that id does not exist";
            
        }

        dutyRepository.deleteById(id);

        return "Duty deleted successfully";
    }

}
