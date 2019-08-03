import { Component, OnInit } from '@angular/core';
import {CompetitionsView} from "../model/competitions-view";
import {DataService} from "../shared/data.service";

@Component({
  selector: 'app-competition-panel',
  templateUrl: './competition-panel.component.html',
  styleUrls: ['./competition-panel.component.css']
})
export class CompetitionPanelComponent implements OnInit {


  selectedCompetition: CompetitionsView;

  constructor(private data: DataService) { }

  ngOnInit() {
    this.data.currentCompetition.subscribe(selectedCompetition => this.selectedCompetition = selectedCompetition);
  }
}
