import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {NewsComponent} from "./components/news/news.component";
import {ToonStatsComponent} from "./components/toonstats/toonstats.component";
import {DistrictsComponent} from "./components/districts/districts.component";
import {FieldOffices} from "./models/field-offices";
import {FieldOfficesComponent} from "./components/field-offices/field-offices.component";

const routes: Routes = [
  { path: '', redirectTo: '/news', pathMatch: 'full'},
  { path: 'news', component: NewsComponent },
  { path: 'toonstats', component: ToonStatsComponent },
  { path: 'districts', component: DistrictsComponent },
  { path: 'field-offices', component: FieldOfficesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
