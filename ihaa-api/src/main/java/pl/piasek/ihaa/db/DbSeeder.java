package pl.piasek.ihaa.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piasek.ihaa.model.Countries;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    private CountriesRepo countriesRepo;
    private ClubsRepo clubsRepo;

    @Autowired
    public DbSeeder(CountriesRepo countriesRepo, ClubsRepo clubsRepo) {
        this.clubsRepo = clubsRepo;
        this.countriesRepo = countriesRepo;
    }

    @Override
    public  void run(String... args) throws Exception {

        List<Countries> countries = new ArrayList<>();

        if(!countriesRepo.existsByName("Poland"))
        countries.add(new Countries("Poland"));
        //countries.add(new Countries("Hungary"));

        countriesRepo.saveAll(countries);
    }


}
