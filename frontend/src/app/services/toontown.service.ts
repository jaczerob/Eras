import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { News } from '../models/news';
import { ReleaseNotes } from '../models/release-notes';
import { FieldOffices } from '../models/field-offices';
import { Status } from '../models/status';
import { Districts } from '../models/districts';
import { ToonStats } from '../models/toonstats';

@Injectable({
  providedIn: 'root'
})
export class ToontownService {
  private toontownUrl: string = '/api/toontown'

  constructor(private http: HttpClient) { }

  public getToonStats(): Observable<ToonStats> {
    return this.get<ToonStats>('/toonstats');
  }

  public getDistricts(): Observable<Districts> {
    return this.get<Districts>('/districts');
  }

  public getNews(): Observable<News> {
    return this.get<News>('/news');
  }

  public getReleaseNotes(): Observable<ReleaseNotes> {
    return this.get<ReleaseNotes>('/releasenotes');
  }

  public getFieldOffices(): Observable<FieldOffices> {
    return this.get<FieldOffices>('/fieldoffices');
  }

  public getStatus(): Observable<Status> {
    return this.get<Status>('/status');
  }

  private get<T>(endpoint: string): Observable<T> {
    return this.http.get<T>(
      environment.baseUrl + this.toontownUrl + endpoint,
      {
        headers: environment.headers,
      }
    )
  }
}
