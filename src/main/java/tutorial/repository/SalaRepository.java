package tutorial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tutorial.entity.Sala;

import java.util.List;

@Repository
public interface SalaRepository extends MongoRepository<Sala, String> {
    List<Sala> findByLokalizacjaContains(String text);
    List<Sala> findByKlasaContains(String text);
    List<Sala> findByNrSali(String text);
}
