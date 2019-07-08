package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Shots;

public interface ShotsRepo extends JpaRepository<Shots, Long> {
}
