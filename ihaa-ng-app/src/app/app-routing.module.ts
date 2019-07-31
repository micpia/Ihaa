import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HorsesComponent} from "./horses/horses.component";
import {LastResultComponent} from "./last-result/last-result.component";
import {NotFoundComponent} from "./not-found/not-found.component";

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
