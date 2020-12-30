package tutorial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tutorial.entity.Film;

import java.util.List;

@Repository
public interface FilmRepository extends MongoRepository<Film, String> {
    List<Film> findByCzasTrwaniaLessThan(String text);
    List<Film> findByTytulContains(String text);
    List<Film> findByGatunekContains(String text);
    List<Film> findByOgraniczenieWiekoweContains(String text);
    List<Film> findByRezyseriaContains(String text);
    List<Film> findByRokProdukcjiLessThan(String text);
}
