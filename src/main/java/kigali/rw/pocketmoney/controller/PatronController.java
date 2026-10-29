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

import kigali.rw.pocketmoney.domain.Patron;
import kigali.rw.pocketmoney.service.PatronService;

@RestController 
@RequestMapping ("/api/patrons")
public class PatronController {

    @Autowired 
    private  PatronService patronService;


    @PostMapping ("/save")
    public  ResponseEntity<?> save(@RequestBody Patron patron){
        String result = patronService.save(patron);

    return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @GetMapping ("/all")
    public  ResponseEntity<?> getAll(){
        List<Patron> result= patronService.getAll();

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public  ResponseEntity<?> getById(@PathVariable  UUID id){
        Optional<Patron> result= patronService.getById(id);

        if (result.isEmpty()) {
            return  new  ResponseEntity<>("The patron with that id does not exist",HttpStatus.NOT_FOUND);
            
        }

        return  new ResponseEntity<>(result.get(),HttpStatus.OK);
    }

    @PutMapping ("/update/{id}")
    public  ResponseEntity<?> update(@PathVariable UUID id, @RequestBody  Patron patron){
        String result= patronService.update(id, patron);

        if (result.equals("The patron with that id does not exist")) {

            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            
        }

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public  ResponseEntity<?> delete(@PathVariable  UUID id){
        String result = patronService.delete(id);

        if (result.equals("The patron with that id does not exist")) {

            return  new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            
        }

        return  new  ResponseEntity<>(result,HttpStatus.OK);
    }

}
