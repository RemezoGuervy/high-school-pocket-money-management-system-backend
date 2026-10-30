package kigali.rw.pocketmoney.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.rw.pocketmoney.domain.Student;
import kigali.rw.pocketmoney.repository.StudentRepository;

@Service 
public class StudentService {
    @Autowired
    private  StudentRepository studentRepository;


    public  String save(Student student){
        studentRepository.save(student);

        return  "Student saved successfully";
    }

    public List<Student> getAll(){
        return  studentRepository.findAll();
    }

    public   Optional<Student> getById(UUID id){
        return  studentRepository.findById(id);
    }

    public String update(UUID id, Student student){
        Optional<Student> studentServe= studentRepository.findById(id);

        if (studentServe.isEmpty()) {


            return "The student with that id does not exist";
            
        }
        Student stud= studentServe.get();

        stud.setFirstName(student.getFirstName());
        stud.setLastName(student.getLastName());
        stud.setStudentNumber(student.getStudentNumber());
        stud.setParentPhone(student.getParentPhone());
        stud.setClassName(student.getClassName());

        studentRepository.save(stud);

        return "Student updated successfully";
    }

    public String delete(UUID id){
        Optional<Student> studentOp= studentRepository.findById(id);

        if (studentOp.isEmpty()) {
            return "The student with that id does not exist";
        }

        studentRepository.deleteById(id);

        return "Student deleted successfully";
    }

    public List<Student> getByLastName(String lastName){
        return studentRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

}
