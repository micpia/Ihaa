import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { HorsesComponent } from './horses/horses.component';
import { LastResultComponent } from './last-result/last-result.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { CountriesComponent } from './countries/countries.component';
import { RidersViewComponent } from './riders-view/riders-view.component';
import {HttpClientModule} from "@angular/common/http";
import { CompetitionsViewComponent } from './competitions-view/competitions-view.component';
import { CompetitionPanelComponent } from './competition-panel/competition-panel.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    HorsesComponent,
    LastResultComponent,
    NotFoundComponent,
    CountriesComponent,
    RidersViewComponent,
    CompetitionsViewComponent,
    CompetitionPanelComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
