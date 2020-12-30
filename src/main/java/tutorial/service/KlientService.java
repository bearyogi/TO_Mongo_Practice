package tutorial.service;

import org.springframework.stereotype.Service;
import tutorial.entity.Klient;
import tutorial.repository.KlientRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class KlientService {
    private static final Logger LOGGER = Logger.getLogger(KlientService.class.getName());
    private KlientRepository klientRepository;

    public KlientService(KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    public List<Klient> findAll() {
        return klientRepository.findAll();
    }

    public List<Klient> findAll(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return klientRepository.findAll();
        } else  {
            return  klientRepository.findByNazwiskoContains(filterText);
        }
    }
    public List<Klient> findAllImie(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return klientRepository.findAll();
        } else {
            return klientRepository.findByImieContains(filterText);
        }
    }
    public List<Klient> findAllAdres(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return klientRepository.findAll();
        } else {
            return klientRepository.findByAdresContains(filterText);
        }
    }
    public List<Klient> findAllEmail(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return klientRepository.findAll();
        } else {
            return klientRepository.findByEmailContains(filterText);
        }
    }
    public long count() {
        return klientRepository.count();
    }

    public void delete(Klient klient) {
        klientRepository.delete(klient);
    }

    public void save(Klient klient) {
        if (klient == null) {
            LOGGER.log(Level.SEVERE,
                    "pusto");
            return;
        }
        klientRepository.save(klient);
    }

}
