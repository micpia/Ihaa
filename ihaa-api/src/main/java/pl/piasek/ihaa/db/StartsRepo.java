package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Starts;

public interface StartsRepo extends JpaRepository<Starts, Long> {
}
