package tutorial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tutorial.entity.Klient;

import java.util.List;

@Repository
public interface KlientRepository extends MongoRepository<Klient, String> {

    List<Klient> findByImieContains(String searchTerm);
    List<Klient> findByNazwiskoContains(String searchTerm);
    List<Klient> findByEmailContains(String searchTerm);
    List<Klient> findByAdresContains(String searchTerm);
}

