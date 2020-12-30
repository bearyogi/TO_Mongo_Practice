package tutorial.service;

import org.springframework.stereotype.Service;
import tutorial.entity.Seans;
import tutorial.repository.SeansRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SeansService {
    private static final Logger LOGGER = Logger.getLogger(KlientService.class.getName());
    private SeansRepository seansRepository;

    public SeansService(SeansRepository seansRepository) {
        this.seansRepository = seansRepository;
    }

    public List<Seans> findAll() {
        return seansRepository.findAll();
    }

    public List<Seans> findAllNapisy(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return seansRepository.findAll();
        } else  {
            return  seansRepository.findByNapisyContains(filterText);
        }
    }
    public List<Seans> findAllLektor(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return seansRepository.findAll();
        } else {
            return seansRepository.findByLektorContains(filterText);
        }
    }

    public List<Seans> findAllData(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return seansRepository.findAll();
        } else {
            return seansRepository.findByDataContains(filterText);
        }
    }

    public List<Seans> findAllGodzina(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return seansRepository.findAll();
        } else {
            return seansRepository.findByGodzinaContains(filterText);
        }
    }
    public long count() {
        return seansRepository.count();
    }

    public void delete(Seans seans) {
        seansRepository.delete(seans);
    }

    public void save(Seans seans) {
        if (seans == null) {
            LOGGER.log(Level.SEVERE,
                    "pusto");
            return;
        }
        seansRepository.save(seans);
    }

}
