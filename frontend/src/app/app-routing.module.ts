import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NewsComponent} from "./components/news/news.component";
import {ToonStatsComponent} from "./components/toonstats/toonstats.component";
import {DistrictsComponent} from "./components/districts/districts.component";
import {FieldOfficesComponent} from "./components/field-offices/field-offices.component";
import {LandingComponent} from "./components/landing/landing.component";
import {ContributorsComponent} from "./components/contributors/contributors.component";

const routes: Routes = [
  { path: '', component: LandingComponent},
  { path: 'news', component: NewsComponent },
  { path: 'toonstats', component: ToonStatsComponent },
  { path: 'districts', component: DistrictsComponent },
  { path: 'field-offices', component: FieldOfficesComponent },
  { path: 'contributors', component: ContributorsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
