package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.RidersView;
import pl.piasek.ihaa.model.RidersViewCompositeKey;

public interface RidersViewRepo extends JpaRepository<RidersView, RidersViewCompositeKey> {
}
