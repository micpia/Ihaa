import { Component, OnInit } from '@angular/core';
import {LastResult} from "../model/last-result";
import {ApiService} from "../shared/api.service";

@Component({
  selector: 'app-last-result',
  templateUrl: './last-result.component.html',
  styleUrls: ['./last-result.component.css']
})
export class LastResultComponent implements OnInit {

  lastResults: LastResult[] = [];

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.getAllLastResults();
  }

  public getAllLastResults() {
    this.apiService.getAllLastResults().subscribe(
      res => {
        this.lastResults = res;
      },
      err => {
        alert("Error has occurred while fetching Last results table.")
      }
    )
  }

}
