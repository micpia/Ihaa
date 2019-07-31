package pl.piasek.ihaa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.piasek.ihaa.db.RidersViewRepo;
import pl.piasek.ihaa.model.RidersView;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/ridersView")
public class RidersViewController {

    private RidersViewRepo ridersViewRepo;

    @Autowired
    public RidersViewController(RidersViewRepo ridersViewRepo) {
        this.ridersViewRepo = ridersViewRepo;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<RidersView> getAll() {
        return  ridersViewRepo.findAll();
    }
}
