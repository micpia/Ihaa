package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Horses;
import pl.piasek.ihaa.model.Shots;

public interface HorsesRepo extends JpaRepository<Horses, Long> {
    boolean existsByName(String name);

    Horses findByName(String name);
}
