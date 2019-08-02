import { Component, OnInit } from '@angular/core';
import {ApiService} from "../shared/api.service";
import {Country} from "../model/country";

@Component({
  selector: 'app-countries',
  templateUrl: './countries.component.html',
  styleUrls: ['./countries.component.css']
})
export class CountriesComponent implements OnInit {

  countries: Country[] = [];

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.getAllCountries();
  }

  public getAllCountries() {
    this.apiService.getAllCountries().subscribe(
      res => {
        this.countries = res;
      },
      err => {
        alert("Error has occurred while fetching Countries table.")
      }
    )
  }
}
