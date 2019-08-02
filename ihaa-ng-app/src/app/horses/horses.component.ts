import { Component, OnInit } from '@angular/core';
import {ApiService} from "../shared/api.service";
import {Horse} from "../model/horse";

@Component({
  selector: 'app-horses',
  templateUrl: './horses.component.html',
  styleUrls: ['./horses.component.css']
})
export class HorsesComponent implements OnInit {

  horses: Horse[] = [];

  constructor(private apiService:ApiService) { }

  ngOnInit() {
    this.getAllHorses();
  }

  public getAllHorses() {
    this.apiService.getAllHorses().subscribe(
      res => {
        this.horses = res;
      },
      error => {
        alert("Error has occurred while fetching horses table.")
      }
    )
  }

}
