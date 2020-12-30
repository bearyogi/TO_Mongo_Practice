package tutorial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tutorial.entity.Miejsce;

import java.util.List;

@Repository
public interface MiejsceRepository extends MongoRepository<Miejsce, String> {
    List<Miejsce> findByNumerRzedu(String text);
    List<Miejsce> findByNumerMiejsca(String text);
    List<Miejsce> findBySala(String text);
}
