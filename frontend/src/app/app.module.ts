import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DistrictsComponent } from './components/districts/districts.component';
import { NewsComponent } from './components/news/news.component';
import { LoginComponent } from './components/login/login.component';
import { FieldOfficesComponent } from './components/field-offices/field-offices.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ToonStatsComponent } from './components/toonstats/toonstats.component';

@NgModule({
  declarations: [
    AppComponent,
    DistrictsComponent,
    NewsComponent,
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
  bootstrap: [AppComponent],
})
export class AppModule { }
