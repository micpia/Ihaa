package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Competitions;

public interface CompetitionsRepo extends JpaRepository<Competitions, Long> {
}
