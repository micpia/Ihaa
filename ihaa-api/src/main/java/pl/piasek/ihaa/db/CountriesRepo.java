package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Countries;

public interface CountriesRepo extends JpaRepository<Countries, Long> {
    boolean existsByName(String name);
    Countries findByName(String name);
}
