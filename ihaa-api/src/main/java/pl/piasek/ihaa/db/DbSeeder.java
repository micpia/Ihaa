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
    private int shotNumber;


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

    @Override
    public void run(String... args) throws Exception {


        //competition data
        Date startDay = Date.valueOf("2019-06-28");
        Competitions competitionsGPS2_19 = new Competitions("Grand Prix Stage 2 Białystok", startDay, false, "Białystok");
        //hungarian style data
        Styles stylesHun = new Styles("Hungarian", 1.0, 9);
        ArrayList<Tracks> tracksListHun = new ArrayList<Tracks>();
        Tracks tracksHun = new Tracks("hungarian", 20, 1, 9, stylesHun, 3);
        tracksListHun.add(tracksHun);

        //hun GPS2 2019
        competitionStyleDataToDb(1,
                2,
                3,
                4,
                "FIELD2",
                competitionsGPS2_19,
                stylesHun,
                tracksListHun,
                "/home/michal/IdeaProjects/Ihaa/ihaa-api/src/main/resources/static/data/json/scoresheet_h1.json");

        //korean style data
        Styles stylesKor = new Styles("Korean", 1.0, 6);
        ArrayList<Tracks> tracksListKor = new ArrayList<Tracks>();
        Tracks tracksKorD = new Tracks("korean double", 14, 1, 2, stylesKor, 2);
        Tracks tracksKorT = new Tracks("korean triple", 18, 3, 4, stylesKor, 3);
        Tracks tracksKorM = new Tracks("korean multiple", 25, 5, 6, stylesKor, 5);
        tracksListKor.add(tracksKorD);
        tracksListKor.add(tracksKorT);
        tracksListKor.add(tracksKorM);

        //korean GPS2 2019
        competitionStyleDataToDb(1,
                2,
                3,
                4,
                "FIELD2",
                competitionsGPS2_19,
                stylesKor,
                tracksListKor,
                "/home/michal/IdeaProjects/Ihaa/ihaa-api/src/main/resources/static/data/json/scoresheet_k1.json");



    }

    private void competitionStyleDataToDb(int ridersRow,    //TODO sheet data to one parameter
                                          int countriesRow,
                                          int horsesRow,
                                          int stylesRow,
                                          String dataField,
                                          Competitions competitions,
                                          Styles styles,
                                          ArrayList<Tracks> tracksList,
                                          String path) {

        int rowsInSheet = styles.getNumberOfRuns() + 7;

        //competitions
        if (!this.competitionsRepo.existsByNameAndStartDay(competitions.getName(), competitions.getStartDay())) {
            this.competitionsRepo.save(competitions);
        }
        competitions.setId(this.competitionsRepo.findByNameAndStartDay(competitions.getName(), competitions.getStartDay()).getId());
        //System.out.println(competitions.getId() + ": " + competitions.getName() + " " + competitions.getStartDay() +  " " + competitions.getStatus() +  " " + competitions.getLocation());

        //styles
        if (!this.stylesRepo.existsByName(styles.getName())) {
            this.stylesRepo.save(styles);
        }
        styles.setId(this.stylesRepo.findByName(styles.getName()).getId());
        //System.out.println(styles.getId() + ": " + styles.getName() + " " + styles.getNumberOfRuns() + " " + styles.getPointsPerSecond());

        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader(path)) {

            Object object = jsonParser.parse(fileReader);
            JSONArray scoreSheets = (JSONArray) object;
            int rowNumber = 0;
            Iterator iterator = scoreSheets.iterator();

            while (iterator.hasNext()) {            //loop thru all scoresheets in file

                if (rowNumber % rowsInSheet == 0) {
                    this.countries = new Countries();
                    this.riders = new Riders();
                    this.horses = new Horses();
                    this.starts = new Starts();
                    this.tracks = new Tracks();
                }

                rowNumber++;
                Iterator<Map.Entry> rowIterator = ((Map) iterator.next()).entrySet().iterator();

                //ArrayList<Integer> runPoints = new ArrayList<Integer>();

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
                } else if (rowNumber % rowsInSheet == stylesRow + 1) { //appending queried data to db

                    //countries
                    if (!this.countriesRepo.existsByName(this.countries.getName())) {
                        this.countriesRepo.save(countries);
                    }
                    this.countries.setId(this.countriesRepo.findByName(this.countries.getName()).getId());
                    //System.out.println(this.countries.getId() +  ": " + this.countries.getName());

                    //riders
                    if (!this.ridesRepo.existsByNameAndSurname(this.riders.getName(), this.riders.getSurname())) {
                        this.riders.setCountriesByCountriesId(countries);
                        this.ridesRepo.save(this.riders);
                    }
                    this.riders.setId(ridesRepo.findByNameAndSurname(riders.getName(), riders.getSurname()).getId());
                    //System.out.println(this.riders.getId() + ": " + this.riders.getName() + " " + this.riders.getSurname());

                    //horses
                    if (!this.horsesRepo.existsByName(this.horses.getName())) {
                        this.horsesRepo.save(this.horses);
                    }
                    this.horses.setId(this.horsesRepo.findByName(this.horses.getName()).getId());
                    //System.out.println(this.horses.getId() + ": " + this.horses.getName());

                    //starts
                    starts.setRidersByRidersId(this.riders);
                    starts.setCompetitionsByCompetitionsId(competitions);
                    starts.setHorsesByHorsesId(this.horses);

                    if (!this.startsRepo.existsStartsByCompetitionsByCompetitionsIdAndRidersByRidersId(this.starts.getCompetitionsByCompetitionsId(), this.starts.getRidersByRidersId())) {
                        this.startsRepo.save(starts);
                    }
                    this.starts.setId(this.startsRepo.findStartsByCompetitionsByCompetitionsIdAndRidersByRidersId(this.starts.getCompetitionsByCompetitionsId(), this.starts.getRidersByRidersId()).getId());
                    //System.out.println(this.starts.getId() + " " + this.starts.getRidersByRidersId().getName() + " " + this.starts.getRidersByRidersId().getSurname() + " " + this.starts.getCompetitionsByCompetitionsId().getName() + " " + this.starts.getHorsesByHorsesId().getName() );


                } else if (rowNumber % rowsInSheet > stylesRow + 1 && rowNumber % rowsInSheet <= stylesRow + 1 + styles.getNumberOfRuns()) {   //if points row in sheet

                    //tracks
                    //assigning proper track to this.tracks
                    int runNumber = rowNumber % rowsInSheet - (stylesRow + 1);
                    for (Tracks track : tracksList) {
                        if (track.getFirstRun() <= runNumber && track.getLastRun() >= runNumber) {
                            this.tracks = track;

                            if (!this.tracksRepo.existsByName(this.tracks.getName())) {
                                this.tracksRepo.save(this.tracks);
                            }
                            this.tracks.setId(this.tracksRepo.findByName(this.tracks.getName()).getId());
                            //System.out.println(this.tracks.getId() + ": " + this.tracks.getName() + " " + this.tracks.getStylesByStylesId().getName());
                        }
                    }

                    int columnNumber = 0;
                    //int shotNo = 0;
                    this.shotNumber = 1;
                    while (rowIterator.hasNext()) {
                        columnNumber++;
                        Map.Entry pair = rowIterator.next();


                        if (columnNumber == 1) {                 // reading time
                            double time = Double.parseDouble((pair.getValue().toString().equals("")) ? "0" : pair.getValue().toString());
                            this.runs = new Runs(time, runNumber, this.starts, this.tracks);
                            if (!this.runsRepo.existsRunsByStartsByStartsIdAndTracksByTracksIdAndRunNumber(this.starts, this.tracks, runNumber)) {
                                this.runsRepo.save(runs);
                            }
                            this.runs.setId(this.runsRepo.findRunsByStartsByStartsIdAndTracksByTracksIdAndRunNumber(this.starts, this.tracks, runNumber).getId());
                        }

                        //shots
                        if (columnNumber >= 2 && columnNumber < this.tracks.getNumberOfTargets() + 2) {

                            String rawPoints = (pair.getValue().toString().equals("")) ? "0" : pair.getValue().toString();
                            String trPoints[] = rawPoints.split("");

                            for (int i = 0; i < trPoints.length; i++) { //String points : trPoints)
                                Shots shots = new Shots(Integer.parseInt(trPoints[i]),this.shotNumber, this.runs);
                                if (!this.shotsRepo.existsShotsByRunsByRunsIdAndShotNumber(this.runs, this.shotNumber)) {
                                    this.shotsRepo.save(shots);
                                }
                                this.shotNumber ++;
                            }
                        }
                    }
                } //else {
//                    rowIterator.next();     //if not info row skip it
//                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
