package pl.piasek.ihaa.db;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piasek.ihaa.model.Countries;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class DbSeeder implements CommandLineRunner {

    private CountriesRepo countriesRepo;
    private ClubsRepo clubsRepo;

    @Autowired
    public DbSeeder(CountriesRepo countriesRepo, ClubsRepo clubsRepo) {
        this.clubsRepo = clubsRepo;
        this.countriesRepo = countriesRepo;
    }

    private ArrayList<String> getFields(int row, int rowsInSheet, String field, String path) {
        JSONParser jsonParser = new JSONParser();

        ArrayList<String> list = new ArrayList<>();

        try (FileReader fileReader = new FileReader(path)) {

            Object object = jsonParser.parse(fileReader);
            JSONArray scoresheets = (JSONArray) object;
//            System.out.println(scoresheets);
//            System.out.println("size: " + scoresheets.size());
            int sheetIterator = 0;
            Iterator iterator = scoresheets.iterator();

            while (iterator.hasNext()) {

                sheetIterator++;
                Iterator<Map.Entry> rowIterator = ((Map) iterator.next()).entrySet().iterator();

                while (rowIterator.hasNext()) {
                    if (sheetIterator % rowsInSheet == row) {
                        
                        Map.Entry pair = rowIterator.next();

                        if (pair.getKey().equals(field)) {
                            list.add(pair.getValue().toString());
                        }
                    } else {
                        rowIterator.next();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Countries> countries = new ArrayList<>();

        if (!countriesRepo.existsByName("Poland"))
            countries.add(new Countries("Poland"));
        //countries.add(new Countries("Hungary"));

        ArrayList<String> names = getFields(1, 16, "FIELD2", "/home/michal/IdeaProjects/Ihaa/ihaa-api/src/main/resources/static/data/json/scoresheet_h1.json");

        System.out.println(names);


        countriesRepo.saveAll(countries);
    }


}
