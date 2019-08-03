import { Component, OnInit } from '@angular/core';
import {CompetitionsView} from "../model/competitions-view";
import {ApiService} from "../shared/api.service";

@Component({
  selector: 'app-competitions-view',
  templateUrl: './competitions-view.component.html',
  styleUrls: ['./competitions-view.component.css']
})
export class CompetitionsViewComponent implements OnInit {

  competitions: CompetitionsView[] = [];

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.getAllCompetitions();
  }

  public getAllCompetitions() {
    this.apiService.getAllCompetitionsView().subscribe(
      res => {
        this.competitions = res;
      },
      err => {
        alert("Error has occurred while fetching competitions view.")
      }
    )
  }
}
