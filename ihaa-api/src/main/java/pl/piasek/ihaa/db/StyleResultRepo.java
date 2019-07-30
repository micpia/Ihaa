package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.StyleResult;
import pl.piasek.ihaa.model.StyleResultCompositeKey;

public interface StyleResultRepo extends JpaRepository<StyleResult, StyleResultCompositeKey> {
}
