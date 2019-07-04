package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Clubs;

public interface ClubsRepo extends JpaRepository<Clubs, Long> {
}
