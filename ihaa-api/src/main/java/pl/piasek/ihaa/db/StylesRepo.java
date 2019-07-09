package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Styles;

public interface StylesRepo extends JpaRepository<Styles, Long> {
    boolean existsByName(String name);

    Styles findByName(String name);
}
