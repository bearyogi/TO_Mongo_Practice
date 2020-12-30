package tutorial.service;

import org.springframework.stereotype.Service;
import tutorial.entity.Bilet;
import tutorial.repository.BiletRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BiletService {
    private static final Logger LOGGER = Logger.getLogger(KlientService.class.getName());
    private BiletRepository biletRepository;

    public BiletService(BiletRepository biletRepository) {
        this.biletRepository = biletRepository;
    }

    public List<Bilet> findAll() {
        return biletRepository.findAll();
    }

    public List<Bilet> findAll(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return biletRepository.findAll();
        } else  {
            return  biletRepository.findByUlgaContains(filterText);
        }
    }
    public List<Bilet> findAllCena(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return biletRepository.findAll();
        } else {
            return biletRepository.findByCenaLessThan(filterText);
        }
    }
    public long count() {
        return biletRepository.count();
    }

    public void delete(Bilet bilet) {
        biletRepository.delete(bilet);
    }

    public void save(Bilet bilet) {
        if (bilet == null) {
            LOGGER.log(Level.SEVERE,
                    "pusto");
            return;
        }
        biletRepository.save(bilet);
    }

}
