package kigali.rw.pocketmoney.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.rw.pocketmoney.domain.Dormitory;
import kigali.rw.pocketmoney.repository.DormitoryRepository;

@Service 
public class DormitoryService {

    @Autowired 
    private  DormitoryRepository dormitoryRepository;


    public  String save(Dormitory dormitory){
        dormitoryRepository.save(dormitory);

        return "Dormitory saved successfully";
    }

    public List<Dormitory> findAll(){
        return dormitoryRepository.findAll();
    }

    public Optional<Dormitory> findById(UUID id){
        return  dormitoryRepository.findById(id);
    }


    public  String update(UUID id, Dormitory dormitory){
        Optional<Dormitory> dormServe= dormitoryRepository.findById(id);

        if (dormServe.isEmpty()) {
            return "The dormitory with that id does not exist";
            
        }

        Dormitory  existDorm = dormServe.get();

        existDorm.setDormNumber(dormitory.getDormNumber());
        existDorm.setName(dormitory.getName());

        dormitoryRepository.save(existDorm);

        return "Dormitory updated successfully";



    }

    public  String delete(UUID id){
        Optional<Dormitory> dormServe = dormitoryRepository.findById(id);

        if (dormServe.isEmpty()) {
            return "The dormitory with that id does not exist";
            
        }

        dormitoryRepository.deleteById(id);

        return "Dormitory deleted successfully";
    }

    public List<Dormitory> getFree(){
        return dormitoryRepository.findFreeDormitories();
    }


}
