package pl.piasek.ihaa.db;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piasek.ihaa.model.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Component
public class DbSeeder implements CommandLineRunner {

    private CountriesRepo countriesRepo;
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
    private Tracks tracks;
    private Runs runs;
    private ArrayList<Runs> runsList;



    @Autowired
    public DbSeeder(CountriesRepo countriesRepo,
                    CompetitionsRepo competitionsRepo,
                    HorsesRepo horsesRepo,
                    ShotsRepo shotsRepo,
                    RidesRepo ridesRepo,
                    RunsRepo runsRepo,
                    StylesRepo stylesRepo,
                    TracksRepo tracksRepo,
                    StartsRepo startsRepo) {
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
                                          ArrayList<Tracks> tracksList,
                                          String path,
                                          int numberOfTargets) {

        int rowsInSheet = styles.getNumberOfRuns() + 7;
        //this.tracks = tracks;

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
                    this.tracks = new Tracks();
                    this.runsList = new ArrayList<Runs>();
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
                } else if(rowNumber % rowsInSheet == stylesRow + 1) { //appending queried data to db

                    //countries
                    if(!this.countriesRepo.existsByName(this.countries.getName())) {
                        this.countriesRepo.save(countries);
                    }
                    this.countries.setId(this.countriesRepo.findByName(this.countries.getName()).getId());
                    System.out.println(this.countries.getId() +  ": " + this.countries.getName());

                    //riders
                    if(!this.ridesRepo.existsByNameAndSurname(this.riders.getName(), this.riders.getSurname())) {
                        this.riders.setCountriesByCountriesId(countries);
                        this.ridesRepo.save(this.riders);
                    }
                    this.riders.setId(ridesRepo.findByNameAndSurname(riders.getName(), riders.getSurname()).getId());
                    System.out.println(this.riders.getId() + ": " + this.riders.getName() + " " + this.riders.getSurname());

                    //horses
                    if(!this.horsesRepo.existsByName(this.horses.getName())) {
                        this.horsesRepo.save(this.horses);
                    }
                    this.horses.setId(this.horsesRepo.findByName(this.horses.getName()).getId());
                    System.out.println(this.horses.getId() + ": " + this.horses.getName());

                    //starts
                    starts.setRidersByRidersId(this.riders);
                    starts.setCompetitionsByCompetitionsId(competitions);
                    starts.setHorsesByHorsesId(this.horses);

                    if(!this.startsRepo.existsStartsByCompetitionsByCompetitionsIdAndRidersByRidersId(this.starts.getCompetitionsByCompetitionsId(), this.starts.getRidersByRidersId()))
                    {
                        this.startsRepo.save(starts);
                    }
                    this.starts.setId(this.startsRepo.findStartsByCompetitionsByCompetitionsIdAndRidersByRidersId(this.starts.getCompetitionsByCompetitionsId(), this.starts.getRidersByRidersId()).getId());
                    System.out.println(this.starts.getId() + " " + this.starts.getRidersByRidersId().getName() + " " + this.starts.getRidersByRidersId().getSurname() + " " + this.starts.getCompetitionsByCompetitionsId().getName() + " " + this.starts.getHorsesByHorsesId().getName() );



                } else if (rowNumber % rowsInSheet > stylesRow + 1 && rowNumber % rowsInSheet <= stylesRow + 1 + styles.getNumberOfRuns()) {   //target points rows

                    //tracks
                    //assigning proper track to this.tracks
                    int runNumber= rowNumber % rowsInSheet - (stylesRow + 1);
                    for(Tracks track: tracksList) {
                        if(track.getFirstRun() <= runNumber  + 1 && track.getLastRun() >= runNumber  + 1) {
                            this.tracks = track;

                            if(!this.tracksRepo.existsByName(this.tracks.getName())) {
                                this.tracksRepo.save(this.tracks);
                            }
                            this.tracks.setId(this.tracksRepo.findByName(this.tracks.getName()).getId());
                            System.out.println(this.tracks.getId() + ": " + this.tracks.getName());
                        }
                    }

                    //runs
                    this.runs = new Runs();

                    int columnNumber = 0;

                    while (rowIterator.hasNext()) {
                        columnNumber++;
                        Map.Entry pair = rowIterator.next();

                        if (columnNumber == 1) {                 // reading time
                            double time = Double.parseDouble((pair.getValue().toString().equals("")) ? "0" : pair.getValue().toString());
                            this.runs.setTime(time);
                            this.runs.setRunNumber(runNumber);
                            int f = 5;
                            //allRunsTimes.add(Double.parseDouble((pair.getValue().toString().equals("")) ? "0" : pair.getValue().toString()));
                        }

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

                    //runs
                    //apply run to run list
                    this.runsList.add(this.runs);
                    int dfgd = 4;

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

                if (rowNumber % rowsInSheet == rowsInSheet - 1) {             //after each scoresheet appending data do db





//                    System.out.println(this.ridersName + " " + this.ridersSurname + " " + this.countriesName + " " + this.horsesName + " " + this.stylesName + " " + this.competitionName);
//                    System.out.println(allRunsPoints);
//                    System.out.println(allRunsTimes);
//
//                    this.allRunsPoints.clear();
//                    this.allRunsTimes.clear();

                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run(String... args) throws Exception {


        //input data for hungarian file
        Date startDay=  Date.valueOf("2019-06-28");
        Competitions competitions = new Competitions("Grand Prix Stage 2 Białystok", startDay, false, "Białystok");
        Styles stylesHun = new Styles("Hungarian", 1.0, 9);
        ArrayList<Tracks> tracksList= new ArrayList<Tracks>();
        Tracks tracksHun = new Tracks("hungarian", 20, 1 , 9, stylesHun);
        tracksList.add(tracksHun);
        //reading hungarian file and appending data to db
        competitionStyleDataToDb(1,
                2,
                3,
                4,
                "FIELD2",
                competitions,
                stylesHun,
                tracksList,
                "/home/michal/IdeaProjects/Ihaa/ihaa-api/src/main/resources/static/data/json/scoresheet_h1.json",
                3);

    }


}
