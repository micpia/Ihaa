import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RidersView} from "../model/riders-view";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private BASE_URL = "http://localhost:8080/api";
  private ALL_RIDERS_URL = `${this.BASE_URL}\\ridersView\\all`;

  constructor(private http: HttpClient) { }

  getAllRiders() : Observable<RidersView[]> {
    return this.http.get<RidersView[]>(this.ALL_RIDERS_URL);
  }
}
