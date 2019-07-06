package pl.piasek.ihaa.db;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import pl.piasek.ihaa.model.Countries;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class DbSeeder implements CommandLineRunner {

    private CountriesRepo countriesRepo;
    private ClubsRepo clubsRepo;

    private String path = null;
    private int numberOfRuns = 9;

    //countries
    private Long countriesId = null;
    private String countriesName = null;

    //riders
    private Long ridersId = null;
    private String ridersName = null;
    private String ridersSurname = null;
    //private Long ridersCountriesId = countriesId;
    //private Long ridersClubsId = null;           // no clubs data in scoresheet

    //horses
    private Long horsesId = null;
    private String horsesName = null;

    //TODO styles variable out of the loop const for all file, input file from only one style. Maybe pass styles data with argument
    //styles
    private Long stylesId = null;
    private String stylesName = null;
    private float stylesPointsPerSecond = 1;

    //tracks
    private Long tracksId = null;
    private String tracksName = null;
    private float tracksTimeLimit = 1;
    //private Long tracksStylesId = stylesId;

    //starts
    private Long startsId;
    //private Long startsRidersId = ridersId;
    //private Long startsCompetitionsId = competitionsId;
    //private Long startsHorsesId = horsesId;

    //competition
    private Long competitionId = null;
    private String competitionName = null;
    private Date competitionsStartDate = null;
    private boolean competitionsStatus = true;
    private String commpetitionsLOcation = null;

    //runs
    private Long runsId = null;
    private float runsTime = 1;
    private DateTimeFormat runsTimestamp = null;
    //private Long runsStartsId = startsId;
    //private Long runsTracksId = tracksId;

    //shots
     

    @Autowired
    public DbSeeder(CountriesRepo countriesRepo, ClubsRepo clubsRepo) {
        this.clubsRepo = clubsRepo;
        this.countriesRepo = countriesRepo;
    }

    private void competitionStyleDataToDb() {

        int ridersRow = 1;                   //some constant data about input table to json format
        int countriesRow = 2;
        int horsesRow = 3;
        int stylesRow = 4;
        String dataField = "FIELD2";

        int rowsInSheet = this.numberOfRuns + 7;


        //TODO competition data input to database

        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader(this.path)) {

            Object object = jsonParser.parse(fileReader);
            JSONArray scoreSheets = (JSONArray) object;
            int sheetIterator = 0;
            Iterator iterator = scoreSheets.iterator();

            while (iterator.hasNext()) {            //loop thru all scoresheets in file
                sheetIterator++;
                Iterator<Map.Entry> rowIterator = ((Map) iterator.next()).entrySet().iterator();

                if (sheetIterator % rowsInSheet == ridersRow) {
                    while (rowIterator.hasNext()) {
                        Map.Entry pair = rowIterator.next();
                        if (pair.getKey().equals(dataField)) {
                            String fullName = pair.getValue().toString();
                            int space = fullName.indexOf(" ");
                            this.ridersName = fullName.substring(0, space);
                            this.ridersSurname = fullName.substring(space + 1);
                        }
                    }
                } else if (sheetIterator % rowsInSheet == countriesRow) {
                    while (rowIterator.hasNext()) {
                        Map.Entry pair = rowIterator.next();
                        if (pair.getKey().equals(dataField)) {
                            this.countriesName = pair.getValue().toString();
                        }
                    }
                } else if (sheetIterator % rowsInSheet == horsesRow) {
                    while (rowIterator.hasNext()) {
                        Map.Entry pair = rowIterator.next();
                        if (pair.getKey().equals(dataField)) {
                            this.horsesName = pair.getValue().toString();
                        }
                    }
                } else if (sheetIterator % rowsInSheet == stylesRow) {
                    while (rowIterator.hasNext()) {
                        Map.Entry pair = rowIterator.next();
                        if (pair.getKey().equals(dataField)) {
                            this.stylesName = pair.getValue().toString();
                        }
                    }
                } else {
                    rowIterator.next();
                }

                if (sheetIterator % rowsInSheet == 0) {
                    System.out.println(this.ridersName + " " + this.ridersSurname + " " + this.countriesName + " " + this.horsesName + " " + this.stylesName + " " + this.competitionName);

                    //TODO input countries, horses, riders, styles, starts data to db and get IDs
                }
            }


//                while (rowIterator.hasNext()) {     //loop thru one sheet
//
//                    if(sheetIterator % rowsInSheet == ridersRow) {       //riders table data
//                        Map.Entry pair = rowIterator.next();
//                        if(pair.getKey().equals(dataField)) {
//                            String fullName = pair.getValue().toString();
//                            int space = fullName.indexOf(" ");
//                            this.ridersName = fullName.substring(0,space);
//                            this.ridersSurname = fullName.substring(space + 1);
//                        }
//                    } else if(sheetIterator % rowsInSheet == countriesRow) {       //countries
//                        Map.Entry pair = rowIterator.next();
//                        if(pair.getKey().equals(dataField)) {
//                            this.countriesName = pair.getValue().toString();
//                        }
//                    } else if(sheetIterator % rowsInSheet == horsesRow) {       //horses table data
//                        Map.Entry pair = rowIterator.next();
//                        if(pair.getKey().equals(dataField)) {
//                            this.horsesName = pair.getValue().toString();
//                        }
//                    } else if(sheetIterator % rowsInSheet == stylesRow) {       //styles table data
//                        Map.Entry pair = rowIterator.next();
//                        if(pair.getKey().equals(dataField)) {
//                            this.stylesName = pair.getValue().toString();
//                        }
//                    } else {
//                        rowIterator.next();
//                    }
//
//
//
////                    if(sheetIterator % rowsInSheet > stylesRow && sheetIterator % rowsInSheet < stylesRow + this.numberOfRuns) {       //runs and shots
////
////                        //TODO manage each run data input with use of earlier fetched IDs
////                    }
//
//
//
//                        //just for reference
////                    if (sheetIterator % rowsInSheet == row) {
////
////                        Map.Entry pair = rowIterator.next();
////
////                        //if (pair.getKey().equals(field) && !list.contains(pair.getValue().toString())) {
////                        //    list.add(pair.getValue().toString());
////                        //}
////                    } else {
////                        rowIterator.next();
////                    }
//
//
//                }



        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
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

        this.competitionName = "Grand Prix Stage 2 Białystok";
        this.path = "/home/michal/IdeaProjects/Ihaa/ihaa-api/src/main/resources/static/data/json/scoresheet_h1.json";
        this.numberOfRuns = 9;
        competitionStyleDataToDb();
        //countries.add(new Countries("Hungary"));
//        ArrayList<String> countries = getFields(2, 16, "FIELD2", "/home/michal/IdeaProjects/Ihaa/ihaa-api/src/main/resources/static/data/json/scoresheet_h1.json");
//        System.out.println("Fetched string array: " + countries);
//        appendArray(countries);
    }


}
