package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Competitions;
import pl.piasek.ihaa.model.Runs;

import java.sql.Date;

public interface CompetitionsRepo extends JpaRepository<Competitions, Long> {
    boolean existsByNameAndStartDay(String name, Date startDay);

    Competitions findByNameAndStartDay(String name, Date startDay);
}
