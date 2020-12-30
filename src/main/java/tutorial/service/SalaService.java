package tutorial.service;

import org.springframework.stereotype.Service;
import tutorial.entity.Sala;
import tutorial.repository.SalaRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SalaService {
    private static final Logger LOGGER = Logger.getLogger(KlientService.class.getName());
    private SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public List<Sala> findAllLokalizacja(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return salaRepository.findAll();
        } else  {
            return  salaRepository.findByLokalizacjaContains(filterText);
        }
    }
    public List<Sala> findAllKlasa(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return salaRepository.findAll();
        } else {
            return salaRepository.findByKlasaContains(filterText);
        }
    }
    public List<Sala> findAllNrSali(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return salaRepository.findAll();
        } else {
            return salaRepository.findByNrSali(filterText);
        }
    }
    public long count() {
        return salaRepository.count();
    }

    public void delete(Sala sala) {
        salaRepository.delete(sala);
    }

    public void save(Sala sala) {
        if (sala == null) {
            LOGGER.log(Level.SEVERE,
                    "pusto");
            return;
        }
        salaRepository.save(sala);
    }

}
