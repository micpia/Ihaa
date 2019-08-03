import {Component, OnInit} from '@angular/core';
import {CompetitionsView} from "../model/competitions-view";
import {ApiService} from "../shared/api.service";
import {BehaviorSubject} from "rxjs";
import {DataService} from "../shared/data.service";

@Component({
  selector: 'app-competitions-view',
  templateUrl: './competitions-view.component.html',
  styleUrls: ['./competitions-view.component.css']
})
export class CompetitionsViewComponent implements OnInit {

  competitions: CompetitionsView[] = [];
  selectedCompetition: CompetitionsView;

  constructor(private apiService: ApiService, private data: DataService) {
  }

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


  selectCompetition(competition: CompetitionsView) {
    this.data.changeCompetition(competition);
    this.data.currentCompetition.subscribe(selectedCompetition => this.selectedCompetition = selectedCompetition);
  }
}
