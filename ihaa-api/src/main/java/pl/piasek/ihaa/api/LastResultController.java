package pl.piasek.ihaa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.piasek.ihaa.db.LastResultRepo;
import pl.piasek.ihaa.model.LastResult;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/lastResult")
public class LastResultController {

    private LastResultRepo lastResultRepo;

    public LastResultController(LastResultRepo lastResultRepo) {
    this.lastResultRepo = lastResultRepo;
    }

    @Autowired
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<LastResult> getAll() {
        return lastResultRepo.findAll();
    }

}
