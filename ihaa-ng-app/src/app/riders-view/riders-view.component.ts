import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-riders-view',
  templateUrl: './riders-view.component.html',
  styleUrls: ['./riders-view.component.css']
})
export class RidersViewComponent implements OnInit {

  riders: RidersViewComponent[] = [];
  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.getAllRiders();
  }

  public getAllRiders() {
    let url = "http://localhost:8080/api/ridersView/all";
    this.http.get<RidersViewComponent[]>(url).subscribe(
      res => {
      this.riders = res;
      },
      error => {
        alert("Error has occurred!");
      }
    )
  }

}
