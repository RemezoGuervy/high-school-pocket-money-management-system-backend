package kigali.rw.pocketmoney.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import org.hibernate.cache.spi.entry.StructuredMapCacheEntry;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kigali.rw.pocketmoney.domain.Patron;
import kigali.rw.pocketmoney.domain.Student;
import kigali.rw.pocketmoney.repository.PatronRepository;
import kigali.rw.pocketmoney.service.PatronService;
import kigali.rw.pocketmoney.service.StudentService;

@RestController 
@RequestMapping ("/api/students")
public class StudentController {

    @Autowired 
    private  StudentService studentService;

    @Autowired 
    private  PatronService patronService;

    @PostMapping ("/save")
    public  ResponseEntity<?> save(@RequestBody  Student student){
        String message = studentService.save(student);
        return new ResponseEntity<>(message, HttpStatus.CREATED); 
    }

    @GetMapping ("/all")
    public ResponseEntity<?> getAll(){
        List<Student> students = studentService.getAll();
        return new  ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getById(@PathVariable  UUID id){
        Optional<Student> studOptional= studentService.getById(id);

        if (studOptional.isEmpty()) {

            return  new ResponseEntity<>("The student with that id does not exist", HttpStatus.NOT_FOUND);
            
        }
        return new ResponseEntity<>(studOptional.get(), HttpStatus.OK);
    }

    @PutMapping ("/update/{id}")
    public  ResponseEntity<?> update(@PathVariable  UUID id, @RequestBody  Student student){
         String studeOptional= studentService.update(id, student);

        if (studeOptional.equals("The student with that id does not exist")) {

            return new ResponseEntity<>(studeOptional, HttpStatus.NOT_FOUND);
            
        }

        return  new ResponseEntity<>(studeOptional, HttpStatus.OK);

    }

    @DeleteMapping ("/delete/{id}")
    public  ResponseEntity<?> delete(@PathVariable UUID id){
        String studentDel = studentService.delete(id);
        if (studentDel.equals("The student with that id does not exist")) {

            return  new  ResponseEntity<>(studentDel, HttpStatus.NOT_FOUND);
            
        }

        return  new ResponseEntity<>(studentDel,HttpStatus.OK);
        }

        @GetMapping ("/by-last-name")
        public  ResponseEntity<?> getByLastName(@RequestParam String lastName){
            List<Student> students = studentService.getByLastName(lastName);
            return  new ResponseEntity<>(students, HttpStatus.OK);
        }

        @GetMapping ("/of-patron/{patronId}")
        public ResponseEntity<?> getStudent(@PathVariable UUID patronId){
            Optional<Patron> patronOp= patronService.getById(patronId);

            if (patronOp.isEmpty()) {

                return  new ResponseEntity<>("The Patron with that id does not exist", HttpStatus.NOT_FOUND);
                
            }

            List<Student> students =studentService.getStudent(patronId);
            return  new  ResponseEntity<>(students, HttpStatus.OK);
        }
       



}
