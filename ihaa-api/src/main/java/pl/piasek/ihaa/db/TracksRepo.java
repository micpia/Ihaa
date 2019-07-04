package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Tracks;

public interface TracksRepo extends JpaRepository<Tracks, Long> {
}
