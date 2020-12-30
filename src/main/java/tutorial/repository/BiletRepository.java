package tutorial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tutorial.entity.Bilet;

import java.util.List;

@Repository
public interface BiletRepository extends MongoRepository<Bilet, String> {
    List<Bilet> findByUlgaContains(String text);
    List<Bilet> findByCenaLessThan(String text);
}
