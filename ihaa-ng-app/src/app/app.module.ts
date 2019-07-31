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

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    HorsesComponent,
    LastResultComponent,
    NotFoundComponent,
    CountriesComponent,
    RidersViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
