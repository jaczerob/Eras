import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DistrictsComponent } from './components/districts/districts.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NewsComponent } from './components/news/news.component';
import { ReleaseNotesComponent } from './components/release-notes/release-notes.component';
import { LoginComponent } from './components/login/login.component';
import { FieldOfficesComponent } from './components/field-offices/field-offices.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ToonStatsComponent } from './components/toonstats/toonstats.component';

@NgModule({
  declarations: [
    AppComponent,
    DistrictsComponent,
    NavbarComponent,
    NewsComponent,
    ReleaseNotesComponent,
    LoginComponent,
    FieldOfficesComponent,
    ToonStatsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
