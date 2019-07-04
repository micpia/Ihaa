package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Riders;

public interface RidesRepo extends JpaRepository<Riders, Long> {
}
