import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RidersView} from "../model/riders-view";
import {Horse} from "../model/horse";
import {Country} from "../model/country";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private BASE_URL = "http://localhost:8080/api";
  private ALL_RIDERS_URL = `${this.BASE_URL}\\ridersView\\all`;
  private ALL_HORSES_URL = `${this.BASE_URL}\\horses\\all`;
  private ALL_COUNTRIES_URL = `${this.BASE_URL}\\countries\\all`;

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
}
