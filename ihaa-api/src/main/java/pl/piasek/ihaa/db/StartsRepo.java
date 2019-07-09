package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Competitions;
import pl.piasek.ihaa.model.Riders;
import pl.piasek.ihaa.model.Starts;
import pl.piasek.ihaa.model.Styles;

public interface StartsRepo extends JpaRepository<Starts, Long> {

    boolean existsStartsByCompetitionsByCompetitionsIdAndRidersByRidersId(Competitions competitionsByCompetitionsId, Riders ridersByRidersId);
    Starts findStartsByCompetitionsByCompetitionsIdAndRidersByRidersId(Competitions competitionsByCompetitionsId, Riders ridersByRidersId);
}
