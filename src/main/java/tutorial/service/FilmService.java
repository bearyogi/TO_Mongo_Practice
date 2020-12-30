package tutorial.service;

import org.springframework.stereotype.Service;
import tutorial.entity.Film;
import tutorial.repository.FilmRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FilmService {
    private static final Logger LOGGER = Logger.getLogger(KlientService.class.getName());
    private FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public List<Film> findAllTytul(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return filmRepository.findAll();
        } else  {
            return  filmRepository.findByTytulContains(filterText);
        }
    }

    public List<Film> findAllGatunek(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return filmRepository.findAll();
        } else {
            return filmRepository.findByGatunekContains(filterText);
        }
    }

    public List<Film> findAllRokProdukcji(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return filmRepository.findAll();
        } else {
            return filmRepository.findByRokProdukcjiLessThan(filterText);
        }
    }
    public List<Film> findAllRezyseria(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return filmRepository.findAll();
        } else {
            return filmRepository.findByRezyseriaContains(filterText);
        }
    }
    public List<Film> findAllOgraniczenie(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return filmRepository.findAll();
        } else {
            return filmRepository.findByOgraniczenieWiekoweContains(filterText);
        }
    }
    public List<Film> findAllCzasTrwania(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return filmRepository.findAll();
        } else {
            return filmRepository.findByCzasTrwaniaLessThan(filterText);
        }
    }

    public long count() {
        return filmRepository.count();
    }

    public void delete(Film film) {
        filmRepository.delete(film);
    }

    public void save(Film film) {
        if (film == null) {
            LOGGER.log(Level.SEVERE,
                    "pusto");
            return;
        }
        filmRepository.save(film);
    }

}
