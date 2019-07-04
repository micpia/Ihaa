package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Horses;

public interface HorsesRepo extends JpaRepository<Horses, Long> {
}
