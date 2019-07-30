package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.LastResult;
import pl.piasek.ihaa.model.LastResultCompositeKey;

public interface LastResultRepo extends JpaRepository<LastResult, LastResultCompositeKey> {
}
