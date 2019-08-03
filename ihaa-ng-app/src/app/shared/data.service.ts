import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {CompetitionsView} from "../model/competitions-view";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  competition: CompetitionsView;
  private selectedCompetition = new BehaviorSubject<CompetitionsView>(this.competition);
  currentCompetition = this.selectedCompetition.asObservable();

  constructor() {
  }

  changeCompetition(competition: CompetitionsView) {
    this.selectedCompetition.next(competition);
  }
}
