package kigali.rw.pocketmoney.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kigali.rw.pocketmoney.domain.Duty;
import kigali.rw.pocketmoney.service.DutyService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping ("/api/duties")
public class DutyController {

    @Autowired 
    private  DutyService dutyService;

    @PostMapping("/save")
    public  ResponseEntity<?> save(@RequestBody Duty duty){
        String message= dutyService.save(duty);
        return  new  ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping ("/all")
    public  ResponseEntity<?> getAll(){
        List<Duty> duties= dutyService.findAll();
        return new ResponseEntity<>(duties, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        Optional<Duty> dutyOpt= dutyService.findById(id);

        if (dutyOpt.isEmpty()) {
            return new ResponseEntity<>("The duty with that id does not exist", HttpStatus.NOT_FOUND);
            
        }
        return  new ResponseEntity<>(dutyOpt.get(), HttpStatus.OK);
    }

    @PutMapping ("/update/{id}")
    public  ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Duty duty){
        String message = dutyService.update(id, duty);

        if (message.equals("The duty with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            
        }
        return  new  ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        String message= dutyService.delete(id);

        if (message.equals("The duty with that id does not exist")) {
            return  new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
            
        }
        return  new ResponseEntity<>(message, HttpStatus.OK);
    }
    
   
    @GetMapping ("/unused")
    public  ResponseEntity<?> getUn(){
        List<Duty>  duties= dutyService.getUn();

        return new ResponseEntity<>(duties, HttpStatus.OK);
    }

}
