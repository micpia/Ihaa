package pl.piasek.ihaa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piasek.ihaa.db.CompetitionStylesRepo;
import pl.piasek.ihaa.db.CompetitionsViewRepo;
import pl.piasek.ihaa.model.CompetitionStyles;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/competitionStyles")
public class CompetitionStylesController {

    private CompetitionStylesRepo competitionStylesRepo;

    @Autowired
    public CompetitionStylesController(CompetitionStylesRepo competitionStylesRepo) {
        this.competitionStylesRepo = competitionStylesRepo;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<CompetitionStyles> getAll() {
        return  competitionStylesRepo.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<CompetitionStyles> getCompetitionStyles(@PathVariable int id) {
    return competitionStylesRepo.findByCompetitionsId(id);
    }
}
