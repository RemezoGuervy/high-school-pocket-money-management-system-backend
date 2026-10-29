package kigali.rw.pocketmoney.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.rw.pocketmoney.domain.Patron;
import kigali.rw.pocketmoney.repository.PatronRepository;

@Service 
public class PatronService {
    @Autowired 
    private PatronRepository patronRepository;

    public  String save(Patron patron){
        patronRepository.save(patron);

        return "Patron saved successfully";
    }

    public List<Patron> getAll(){
        return  patronRepository.findAll();
        
    }

    public Optional<Patron> getById(UUID id){
        return  patronRepository.findById(id);
    }

    public String update(UUID id, Patron patron){
        Optional<Patron> patronServ= patronRepository.findById(id);

        if (patronServ.isEmpty()) {

            return "The patron with that id does not exist";
            
        }

        Patron patron2= patronServ.get();

        patron2.setFirstName(patron.getFirstName());
        patron2.setLastName(patron.getLastName());
        patron2.setPhone(patron.getPhone());

        patronRepository.save(patron2);

        return "Patron updated successfully";
    }

    public  String delete(UUID id){
        Optional<Patron> patronServ = patronRepository.findById(id);

        if (patronServ.isEmpty()) {

            return  "The patron with that id does not exist";
            
        }

        patronRepository.deleteById(id);
        return "Patron deleted successfully";
    }

}
