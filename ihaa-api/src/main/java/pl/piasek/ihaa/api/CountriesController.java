package pl.piasek.ihaa.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.piasek.ihaa.db.CountriesRepo;
import pl.piasek.ihaa.model.Countries;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/countries")
public class CountriesController {

    private CountriesRepo countriesRepo;

    @Autowired
    public CountriesController(CountriesRepo countriesRepo) {
        this.countriesRepo = countriesRepo;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Countries> getAll() {
        return countriesRepo.findAll();
    }
}
