package tutorial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tutorial.entity.Seans;

import java.util.List;

@Repository
public interface SeansRepository extends MongoRepository<Seans, String> {
    List<Seans> findByLektorContains(String text);
    List<Seans> findByNapisyContains(String text);
    List<Seans> findByDataContains(String text);
    List<Seans> findByGodzinaContains(String text);
}

