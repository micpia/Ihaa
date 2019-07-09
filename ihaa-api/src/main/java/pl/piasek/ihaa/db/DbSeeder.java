package pl.piasek.ihaa.db;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import pl.piasek.ihaa.model.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DbSeeder implements CommandLineRunner {

    private CountriesRepo countriesRepo;
    private ClubsRepo clubsRepo;
    private CompetitionsRepo competitionsRepo;
    private HorsesRepo horsesRepo;
    private ShotsRepo shotsRepo;
    private RidesRepo ridesRepo;
    private RunsRepo runsRepo;
    private StylesRepo stylesRepo;
    private TracksRepo tracksRepo;
    private StartsRepo startsRepo;

    private Countries countries;
    private Riders riders;
    private Horses horses;
    private Starts starts;


    @Autowired
    public DbSeeder(CountriesRepo countriesRepo,
                    ClubsRepo clubsRepo,
                    CompetitionsRepo competitionsRepo,
                    HorsesRepo horsesRepo,
                    ShotsRepo shotsRepo,
                    RidesRepo ridesRepo,
                    RunsRepo runsRepo,
                    StylesRepo stylesRepo,
                    TracksRepo tracksRepo,
                    StartsRepo startsRepo) {
        this.clubsRepo = clubsRepo;
        this.countriesRepo = countriesRepo;
        this.competitionsRepo = competitionsRepo;
        this.horsesRepo = horsesRepo;
        this.shotsRepo = shotsRepo;
        this.ridesRepo = ridesRepo;
        this.runsRepo = runsRepo;
        this.stylesRepo = stylesRepo;
        this.tracksRepo = tracksRepo;
        this.startsRepo = startsRepo;
    }

    private void competitionStyleDataToDb(int ridersRow,
                                          int countriesRow,
                                          int horsesRow,
                                          int stylesRow,
                                          String dataField,
                                          Competitions competitions,
                                          Styles styles,
                                          String path,
                                          int numberOfTargets) {

        int rowsInSheet = styles.getNumberOfRuns() + 7;

        //TODO competition data input to database

        //competitions
        if(!this.competitionsRepo.existsByNameAndStartDay(competitions.getName(), competitions.getStartDay())) {
            this.competitionsRepo.save(competitions);
        }
        competitions.setId(this.competitionsRepo.findByNameAndStartDay(competitions.getName(), competitions.getStartDay()).getId());
        System.out.println(competitions.getId() + ": " + competitions.getName() + " " + competitions.getStartDay() +  " " + competitions.getStatus() +  " " + competitions.getLocation());

        //styles
        if(!this.stylesRepo.existsByName(styles.getName())) {
            this.stylesRepo.save(styles);
        }
        styles.setId(this.stylesRepo.findByName(styles.getName()).getId());
        System.out.println(styles.getId() + ": " + styles.getName() + " " + styles.getNumberOfRuns() + " " + styles.getPointsPerSecond());

        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader(path)) {

            Object object = jsonParser.parse(fileReader);
            JSONArray scoreSheets = (JSONArray) object;
            int rowNumber = 0;
            Iterator iterator = scoreSheets.iterator();

            while (iterator.hasNext()) {            //loop thru all scoresheets in file

                if(rowNumber % rowsInSheet == 0) {
                    this.countries = new Countries();
                    this.riders = new Riders();
                    this.horses = new Horses();
                    this.starts =  new Starts();
                }

                rowNumber++;
                Iterator<Map.Entry> rowIterator = ((Map) iterator.next()).entrySet().iterator();

                ArrayList<Integer> runPoints = new ArrayList<Integer>();

                if (rowNumber % rowsInSheet == ridersRow) {                     //riders info row
                    while (rowIterator.hasNext()) {
                        Map.Entry pair = rowIterator.next();
                        if (pair.getKey().equals(dataField)) {
                            String fullName = pair.getValue().toString();
                            int space = fullName.indexOf(" ");
                            this.riders.setName(fullName.substring(0, space));
                            this.riders.setSurname(fullName.substring(space + 1));
                        }
                    }
                } else if (rowNumber % rowsInSheet == countriesRow) {           //countries info row
                    while (rowIterator.hasNext()) {
                        Map.Entry pair = rowIterator.next();
                        if (pair.getKey().equals(dataField)) {
                            this.countries.setName(pair.getValue().toString());
                        }
                    }
                } else if (rowNumber % rowsInSheet == horsesRow) {              //horse info row
                    while (rowIterator.hasNext()) {
                        Map.Entry pair = rowIterator.next();
                        if (pair.getKey().equals(dataField)) {
                            this.horses.setName(pair.getValue().toString());
                        }
                    }
                }
//                else if (rowNumber % rowsInSheet == stylesRow) {              //styles info row
//                    while (rowIterator.hasNext()) {
//                        Map.Entry pair = rowIterator.next();
//                        if (pair.getKey().equals(dataField)) {
////                            this.stylesName = pair.getValue().toString();
//                        }
//                    }
//                }
                else if (rowNumber % rowsInSheet > stylesRow + 1 && rowNumber % rowsInSheet <= stylesRow + 1 + styles.getNumberOfRuns()) {   //target points rows
                    int columnNumber = 0;
                    while (rowIterator.hasNext()) {
                        columnNumber++;
                        Map.Entry pair = rowIterator.next();


//                        if (columnNumber == 1) {                 // reading time
//                            allRunsTimes.add(Double.parseDouble((pair.getValue().toString().equals("")) ? "0" : pair.getValue().toString()));
//                        }
//
//                        if (columnNumber >= 2 && columnNumber < numberOfTargets + 2) {            //reading target points, separating them and inserting to one run list TODO change hardcoded 5 for claculated naumber of columns
//                            //5 possible to pass number of targts with argument- may be problems with different number of columns in one style
//
//                            String rawPoints = (pair.getValue().toString().equals("")) ? "0" : pair.getValue().toString();
//                            String trPoints[] = rawPoints.split("");
//
//                            for (String points : trPoints) {
//                                //System.out.println(points);
//                                runPoints.add(Integer.parseInt(points));
//                            }
//                        }

                    }

//                    this.allRunsPoints.add(runPoints);      // adding each run points to List of lists with all target points
//                    //runPoints.clear();
//                    if (rowNumber % rowsInSheet == stylesRow + 1 + this.numberOfRuns) {         // after all point info rows
//
//
//                        //System.out.println(runPoints);
////                        System.out.println(allRunsPoints);
////                        System.out.println(allRunsTimes);
//
//                    }
                } else {
                    rowIterator.next();     //if not info row skip it
                }

                if (rowNumber % rowsInSheet == rowsInSheet - 1) {             //after each scoresheet operationscoountr

                    //countries
                    if(!this.countriesRepo.existsByName(this.countries.getName())) {
                        this.countriesRepo.save(countries);
                    }
                    this.countries.setId(this.countriesRepo.findByName(this.countries.getName()).getId());
                    System.out.println(this.countries.getId() +  ": " + this.countries.getName());

                    //riders
                    if(!this.ridesRepo.existsByNameAndSurname(this.riders.getName(), this.riders.getSurname())) {
                        this.riders.setCountriesByCountriesId(countries);
                        this.ridesRepo.save(riders);
                    }
                    this.riders.setId(ridesRepo.findByNameAndSurname(riders.getName(), riders.getSurname()).getId());
                    System.out.println(this.riders.getId() + ": " + this.riders.getName() + " " + this.riders.getSurname());

                    //horses
                    if(!this.horsesRepo.existsByName(this.horses.getName())) {
                        this.horsesRepo.save(horses);
                    }
                    this.horses.setId(this.horsesRepo.findByName(this.horses.getName()).getId());
                    System.out.println(this.horses.getId() + ": " + this.horses.getName());



                    //starts
//                    if(this.startsRepo.existByRidersIDAndCompetirionsId(this.riders.getId(), this.competitions.getId())) {
//
//                    }


//                    System.out.println(this.ridersName + " " + this.ridersSurname + " " + this.countriesName + " " + this.horsesName + " " + this.stylesName + " " + this.competitionName);
//                    System.out.println(allRunsPoints);
//                    System.out.println(allRunsTimes);
//
//
//                    if (!countriesRepo.existsByName(this.countriesName)) {
//                        countriesRepo.save(new Countries(this.countriesName));
//                    }
//                    System.out.println(countriesRepo.findByName(this.countriesName).getId());
//
//
//
//
//
//                    this.allRunsPoints.clear();
//                    this.allRunsTimes.clear();

                }
            }


//
////                    if(rowNumber % rowsInSheet > stylesRow && rowNumber % rowsInSheet < stylesRow + this.numberOfRuns) {       //runs and shots
////
////
////                    }
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


        Date startDay=  Date.valueOf("2019-06-28");

        Competitions competitions = new Competitions("Grand Prix Stage 2 Białystok", startDay, false, "Białystok");

        Styles styles = new Styles("Hungarian", 1.0, 9);

        competitionStyleDataToDb(1,
                2,
                3,
                4,
                "FIELD2",
                competitions,
                styles,
                "/home/michal/IdeaProjects/Ihaa/ihaa-api/src/main/resources/static/data/json/scoresheet_h1.json",
                3); //error for korean track - different number of targets on runs
        //countries.add(new Countries("Hungary"));
//        ArrayList<String> countries = getFields(2, 16, "FIELD2", "/home/michal/IdeaProjects/Ihaa/ihaa-api/src/main/resources/static/data/json/scoresheet_h1.json");
//        System.out.println("Fetched string array: " + countries);
//        appendArray(countries);
    }


}
