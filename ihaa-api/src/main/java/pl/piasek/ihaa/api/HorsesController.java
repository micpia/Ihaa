package pl.piasek.ihaa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.piasek.ihaa.db.HorsesRepo;
import pl.piasek.ihaa.model.Horses;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/horses")
public class HorsesController {

    private HorsesRepo horsesRepo;

    @Autowired
    public HorsesController(HorsesRepo horsesRepo) {
        this.horsesRepo = horsesRepo;
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Horses> getAll() {
        return horsesRepo.findAll();
    }

}
