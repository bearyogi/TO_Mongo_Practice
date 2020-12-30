package tutorial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tutorial.entity.Zamowienie;

import java.util.List;

@Repository
public interface ZamowienieRepository extends MongoRepository<Zamowienie, String> {
    List<Zamowienie> findByTypPlatnosciContains(String text);
    List<Zamowienie> findByStatusContains(String text);
    List<Zamowienie> findByLiczbaBiletowContains(String text);
    List<Zamowienie> findByKwotaLessThan(String text);
}

