package pl.piasek.ihaa.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasek.ihaa.db.RidersViewRepo;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/ridersView")
public class RidersViewController {

    private RidersViewRepo ridersViewRepo;

    public RidersViewController(RidersViewRepo ridersViewRepo) {
        this.ridersViewRepo = ridersViewRepo;
    }
}
