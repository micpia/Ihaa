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

    private void readAndAppendFile(String path, int numberOfRuns, String competitionName) {

        int ridersRow = 1;                   //some constant data about input table to json format
        int countriesRow = 2;
        int horsesRow = 3;
        int stylesRow = 4;
        String dataField = "FIELD2";

        int rowsInSheet = numberOfRuns + 7;


        //competition
        Long competitionId = null;
        //TODO competition data input to database

        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader(path)) {

            Object object = jsonParser.parse(fileReader);
            JSONArray scoreSheets = (JSONArray) object;

//            System.out.println(scoresheets);
//            System.out.println("size: " + scoresheets.size());
            int sheetIterator = 0;
            Iterator iterator = scoreSheets.iterator();

            while (iterator.hasNext()) {            //loop thru all scoresheets in file

                sheetIterator++;
                Iterator<Map.Entry> rowIterator = ((Map) iterator.next()).entrySet().iterator();

                while (rowIterator.hasNext()) {     //loop thru one sheet

                    //countries
                    Long countriesId = null;
                    String countriesName = null;

                    //riders
                    Long ridersId = null;
                    String ridersName = null;
                    String ridersSurname = null;
                    //Long ridersCountriesId = countriesId;
                    //Long ridersClubsId = null;           // no clubs data in scoresheet

                    //horses
                    Long horsesId = null;
                    String horsesName = null;

                    //TODO styles variable out of the loop const for all file, input file from only one style. Maybe pass styles data with argument
                    //styles
                    Long stylesId = null;
                    String stylesName = null;
                    float stylesPointsPerSecond = 1;

                    //tracks
                    Long tracksId = null;
                    String tracksName = null;
                    float tracksTimeLimit = 1;
                    //Long tracksStylesId = stylesId;

                    //starts
                    Long startsId;
                    //Long startsRidersId = ridersId;
                    //Long startsCompetitionsId = competitionsId;
                    //Long startsHorsesId = horsesId;

                    if(sheetIterator % rowsInSheet == ridersRow) {       //riders table data
                        Map.Entry pair = rowIterator.next();
                        if(pair.getKey().equals(dataField)) {
                            String fullName = pair.getValue().toString();
                            int space = fullName.indexOf(" ");
                            ridersName = fullName.substring(0,space);
                            ridersSurname = fullName.substring(space + 1);
                        } else {
                            rowIterator.next();
                        }
                    }

                    if(sheetIterator % rowsInSheet == countriesRow) {       //countries
                        Map.Entry pair = rowIterator.next();
                        if(pair.getKey().equals(dataField)) {
                            countriesName = pair.getValue().toString();
                        } else {
                            rowIterator.next();
                        }
                    }

                    if(sheetIterator % rowsInSheet == horsesRow) {       //horses table data
                        Map.Entry pair = rowIterator.next();
                        if(pair.getKey().equals(dataField)) {
                            horsesName = pair.getValue().toString();
                        } else {
                            rowIterator.next();
                        }
                    }

                    if(sheetIterator % rowsInSheet == stylesRow) {       //styles table data
                        Map.Entry pair = rowIterator.next();
                        if(pair.getKey().equals(dataField)) {
                            stylesName = pair.getValue().toString();
                        } else {
                            rowIterator.next();
                        }
                    }

                    //TODO input countries, horses, riders, styles, starts data to db and get IDs

                    if(sheetIterator % rowsInSheet > stylesRow && sheetIterator % rowsInSheet < stylesRow + numberOfRuns) {       //runs and shots

                        //TODO manage each run data input with use of earlier fetched IDs
                    }
                        //just for reference
//                    if (sheetIterator % rowsInSheet == row) {
//
//                        Map.Entry pair = rowIterator.next();
//
//                        //if (pair.getKey().equals(field) && !list.contains(pair.getValue().toString())) {
//                        //    list.add(pair.getValue().toString());
//                        //}
//                    } else {
//                        rowIterator.next();
//                    }

                }
            }
        }
    }
//reference for faster changes in code, delete later
//    private void appendArray(ArrayList<String> array) {
//
//        List<Countries> countries = new ArrayList<>();
//
//        int count = 0;
//        while(array.size() > count) {
//
//            System.out.println(array.get(count));
//            if (!countriesRepo.existsByName(array.get(count))) {
//                countries.add(new Countries(array.get(count)));
//            }
//
//            count ++;
//        }
//        countriesRepo.saveAll(countries);
//    }

    @Override
    public void run(String... args) throws Exception {


        //countries.add(new Countries("Hungary"));
//        ArrayList<String> countries = getFields(2, 16, "FIELD2", "/home/michal/IdeaProjects/Ihaa/ihaa-api/src/main/resources/static/data/json/scoresheet_h1.json");
//        System.out.println("Fetched string array: " + countries);
//        appendArray(countries);
    }


}
