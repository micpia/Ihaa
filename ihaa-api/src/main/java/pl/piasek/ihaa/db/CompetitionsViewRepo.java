package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.CompetitionsView;

public interface CompetitionsViewRepo extends JpaRepository<CompetitionsView, Long> {
}
