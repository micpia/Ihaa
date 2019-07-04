package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Runs;

public interface RunsRepo extends JpaRepository<Runs, Long> {
}
