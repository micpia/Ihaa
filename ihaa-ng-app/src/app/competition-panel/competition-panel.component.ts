import { Component, OnInit } from '@angular/core';
import {CompetitionsView} from "../model/competitions-view";
import {DataService} from "../shared/data.service";
import {ApiService} from "../shared/api.service";
import {CompetitionStyles} from "../model/competition-styles";

@Component({
  selector: 'app-competition-panel',
  templateUrl: './competition-panel.component.html',
  styleUrls: ['./competition-panel.component.css']
})
export class CompetitionPanelComponent implements OnInit {


  selectedCompetition: CompetitionsView;
  styles: CompetitionStyles[] = [];

  constructor(private data: DataService, private apiService: ApiService) { }

  ngOnInit() {
    this.data.currentCompetition.subscribe(selectedCompetition => this.selectedCompetition = selectedCompetition);
    this.getStyles(this.selectedCompetition.id);
  }

  getStyles(id: number) {
    this.apiService.getStylesByCompetitionId(id).subscribe(
      res => {
        this.styles = res;
      },
      err => {
        alert("Error occurred while fetching styles view.")
      }
    )
  }
}
