import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RidersView} from "../model/riders-view";
import {Horse} from "../model/horse";
import {Country} from "../model/country";
import {LastResult} from "../model/last-result";
import {CompetitionsView} from "../model/competitions-view";
import {CompetitionStyles} from "../model/competition-styles";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private BASE_URL = "http://localhost:8080/api";
  private ALL_RIDERS_URL = `${this.BASE_URL}\\ridersView\\all`;
  private ALL_HORSES_URL = `${this.BASE_URL}\\horses\\all`;
  private ALL_COUNTRIES_URL = `${this.BASE_URL}\\countries\\all`;
  private ALL_LAST_RESULT_URL = `${this.BASE_URL}\\lastResult\\all`;
  private ALL_COMPETITIONS_URL = `${this.BASE_URL}\\competitionsView\\all`;
  private COMPETITION_STYLES_URL = `${this.BASE_URL}\\competitionStyles\\`;

  constructor(private http: HttpClient) { }

  getAllRiders() : Observable<RidersView[]> {
    return this.http.get<RidersView[]>(this.ALL_RIDERS_URL);
  }

  getAllHorses() : Observable<Horse[]> {
    return  this.http.get<Horse[]>(this.ALL_HORSES_URL);
  }

  getAllCountries() : Observable<Country[]> {
    return  this.http.get<Country[]>(this.ALL_COUNTRIES_URL);
  }

  getAllLastResults() : Observable<LastResult[]> {
    return this.http.get<LastResult[]>(this.ALL_LAST_RESULT_URL);
  }

  getAllCompetitionsView() : Observable<CompetitionsView[]> {
    return this.http.get<CompetitionsView[]>(this.ALL_COMPETITIONS_URL);
  }

  getStylesByCompetitionId(competitionId: number) : Observable<CompetitionStyles[]> {
    return this.http.get<CompetitionStyles[]>(this.COMPETITION_STYLES_URL + competitionId)
  }
}
