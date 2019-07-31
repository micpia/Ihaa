import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HorsesComponent} from "./horses/horses.component";
import {LastResultComponent} from "./last-result/last-result.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {CountriesComponent} from "./countries/countries.component";
import {RidersViewComponent} from "./riders-view/riders-view.component";

const routes: Routes = [
  {
    path:'horses',
    component:HorsesComponent
  },
  {
    path:'lastResult',
    component:LastResultComponent
  },
  {
    path:'countries',
    component:CountriesComponent
  },
  {
    path:'ridersView',
    component:RidersViewComponent
  },
  {
    path:'',
    component:LastResultComponent,
    pathMatch:'full'
  },
  {
    path:'**',
    component:NotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing:false})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
