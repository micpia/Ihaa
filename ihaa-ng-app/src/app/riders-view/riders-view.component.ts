import {Component, OnInit} from '@angular/core';
import {ApiService} from "../shared/api.service";
import {RidersView} from "../model/riders-view";

@Component({
  selector: 'app-riders-view',
  templateUrl: './riders-view.component.html',
  styleUrls: ['./riders-view.component.css']
})
export class RidersViewComponent implements OnInit {

  riders: RidersView[] = [];
  constructor(private apiService:ApiService) {
  }

  ngOnInit() {
    this.getAllRiders();
  }

  public getAllRiders() {

    this.apiService.getAllRiders().subscribe(
      res => {
      this.riders = res;
      },
      error => {
        alert("Error has occurred!");
      }
    )
  }

}
