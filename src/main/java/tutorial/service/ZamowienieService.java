package tutorial.service;

import org.springframework.stereotype.Service;
import tutorial.entity.Zamowienie;
import tutorial.repository.ZamowienieRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ZamowienieService {
    private static final Logger LOGGER = Logger.getLogger(KlientService.class.getName());
    private ZamowienieRepository zamowienieRepository;

    public ZamowienieService(ZamowienieRepository zamowienieRepository) {
        this.zamowienieRepository = zamowienieRepository;
    }

    public List<Zamowienie> findAll() {
        return zamowienieRepository.findAll();
    }

    public List<Zamowienie> findAllTypPlatnosci(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return zamowienieRepository.findAll();
        } else  {
            return  zamowienieRepository.findByTypPlatnosciContains(filterText);
        }
    }
    public List<Zamowienie> findAllStatus(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return zamowienieRepository.findAll();
        } else {
            return zamowienieRepository.findByStatusContains(filterText);
        }
    }

    public List<Zamowienie> findAllLiczbaBiletow(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return zamowienieRepository.findAll();
        } else {
            return zamowienieRepository.findByLiczbaBiletowContains(filterText);
        }
    }

    public List<Zamowienie> findAllKwota(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return zamowienieRepository.findAll();
        } else {
            return zamowienieRepository.findByKwotaLessThan(filterText);
        }
    }

    public List<Zamowienie> findAllKlient(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return zamowienieRepository.findAll();
        } else {
            return zamowienieRepository.findByKlientContains(filterText);
        }
    }

    public List<Zamowienie> findAllSeans(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return zamowienieRepository.findAll();
        } else {
            return zamowienieRepository.findBySeansContains(filterText);
        }
    }
    public long count() {
        return zamowienieRepository.count();
    }

    public void delete(Zamowienie zamowienie) {
        zamowienieRepository.delete(zamowienie);
    }

    public void save(Zamowienie zamowienie) {
        if (zamowienie == null) {
            LOGGER.log(Level.SEVERE,
                    "pusto");
            return;
        }
        zamowienieRepository.save(zamowienie);
    }

}
