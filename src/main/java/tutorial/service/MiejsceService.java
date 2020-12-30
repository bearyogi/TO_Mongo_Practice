package tutorial.service;

import org.springframework.stereotype.Service;
import tutorial.entity.Miejsce;
import tutorial.repository.MiejsceRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MiejsceService {
    private static final Logger LOGGER = Logger.getLogger(KlientService.class.getName());
    private MiejsceRepository miejsceRepository;

    public MiejsceService(MiejsceRepository miejsceRepository) {
        this.miejsceRepository = miejsceRepository;
    }

    public List<Miejsce> findAll() {
        return miejsceRepository.findAll();
    }

    public List<Miejsce> findAll(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return miejsceRepository.findAll();
        } else  {
            return  miejsceRepository.findByNumerRzedu(filterText);
        }
    }
    public List<Miejsce> findAllCena(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return miejsceRepository.findAll();
        } else {
            return miejsceRepository.findByNumerMiejsca(filterText);
        }
    }
    public List<Miejsce> findAllSala(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return miejsceRepository.findAll();
        } else {
            return miejsceRepository.findBySala(filterText);
        }
    }
    public long count() {
        return miejsceRepository.count();
    }

    public void delete(Miejsce miejsce) {
        miejsceRepository.delete(miejsce);
    }

    public void save(Miejsce miejsce) {
        if (miejsce == null) {
            LOGGER.log(Level.SEVERE,
                    "pusto");
            return;
        }
        miejsceRepository.save(miejsce);
    }

}
