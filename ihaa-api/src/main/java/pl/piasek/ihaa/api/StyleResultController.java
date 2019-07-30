package pl.piasek.ihaa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.piasek.ihaa.db.StyleResultRepo;
import pl.piasek.ihaa.model.StyleResult;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/styleResult")
public class StyleResultController {

    private StyleResultRepo styleResultRepo;

    @Autowired
    public StyleResultController(StyleResultRepo styleResultRepo) {
    this.styleResultRepo = styleResultRepo;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<StyleResult> getAll() {
        return styleResultRepo.findAll();
    }
}
