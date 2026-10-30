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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kigali.rw.pocketmoney.domain.Dormitory;
import kigali.rw.pocketmoney.service.DormitoryService;

@RestController 
@RequestMapping ("/api/dormitories")
public class DormitoryController {

    @Autowired 
    private  DormitoryService dormitoryService;


    @PostMapping ("/save")
    public  ResponseEntity<?> save(@RequestBody  Dormitory dormitory){
        String message = dormitoryService.save(dormitory);

        return  new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping ("/all")
    public ResponseEntity<?> getAll(){
        List<Dormitory> dormitories= dormitoryService.findAll();
        return new ResponseEntity<>(dormitories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        Optional<Dormitory> dormOp= dormitoryService.findById(id);

        if (dormOp.isEmpty()) {
            return  new  ResponseEntity<>("The dormitory with that id does not exist", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dormOp.get(),HttpStatus.OK);
    }


    @PutMapping ("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody  Dormitory dormitory ){
        String message = dormitoryService.update(id, dormitory);

        if (message.equals("The dormitory with that id does not exist")) {

            return  new  ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            
        }

        return  new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public  ResponseEntity<?> delete(@PathVariable UUID id){
        String message = dormitoryService.delete(id);

        if (message.equals("The dormitory with that id does not exist")) {
            return new  ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            
        }

        return  new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping ("/free")
    public  ResponseEntity<?> getFree(){
        List<Dormitory> dorm= dormitoryService.getFree();
        return new ResponseEntity<>(dorm, HttpStatus.OK);
    }

}
