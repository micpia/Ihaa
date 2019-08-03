package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.CompetitionStyles;
import pl.piasek.ihaa.model.CompetitionStylesCompositeKey;

import java.util.List;

public interface CompetitionStylesRepo extends JpaRepository<CompetitionStyles, CompetitionStylesCompositeKey> {
    List<CompetitionStyles> findByCompetitionsId(int id);
}
