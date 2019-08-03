package pl.piasek.ihaa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.piasek.ihaa.db.CompetitionsViewRepo;
import pl.piasek.ihaa.model.CompetitionsView;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/competitionsView")
public class CompetitionsViewController {

    private CompetitionsViewRepo competitionsViewRepo;

    @Autowired
    public CompetitionsViewController(CompetitionsViewRepo competitionsViewRepo) {
        this.competitionsViewRepo = competitionsViewRepo;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<CompetitionsView> getAll() {
        return competitionsViewRepo.findAll();
    }


}
