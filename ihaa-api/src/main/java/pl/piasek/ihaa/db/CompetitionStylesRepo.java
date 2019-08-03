package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.CompetitionStyles;

import java.util.List;

public interface CompetitionStylesRepo extends JpaRepository<CompetitionStyles, Long> {
    List<CompetitionStyles> findByCompetitionsId(int id);
}
