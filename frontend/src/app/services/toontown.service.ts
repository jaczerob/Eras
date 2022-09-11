import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Population } from '../models/population';
import { environment } from 'src/environments/environment';
import { News } from '../models/news';
import { ReleaseNotes } from '../models/release-notes';
import { FieldOffices } from '../models/field-offices';
import { LoginInfo } from '../models/login-info';

@Injectable({
  providedIn: 'root'
})
export class ToontownService {
  private toontownUrl: string = '/api/toontown'

  constructor(private http: HttpClient) { }

  public getPopulation(): Observable<Population> {
    return this.http.get<Population>(
      environment.baseUrl + this.toontownUrl + '/population',
      {
        headers: environment.headers,
        withCredentials: environment.withCredentials
      }
    );
  }

  public getNews(): Observable<News> {
    return this.http.get<News>(
      environment.baseUrl + this.toontownUrl + '/news',
      {
        headers: environment.headers,
        withCredentials: environment.withCredentials
      }
    );
  }

  public getReleaseNotes(): Observable<ReleaseNotes> {
    return this.http.get<ReleaseNotes>(
      environment.baseUrl + this.toontownUrl + '/releasenotes',
      {
        headers: environment.headers,
        withCredentials: environment.withCredentials
      }
    );
  }

  public getFieldOffices(): Observable<FieldOffices> {
    return this.http.get<FieldOffices>(
      environment.baseUrl + this.toontownUrl + '/fieldoffices',
      {
        headers: environment.headers,
        withCredentials: environment.withCredentials
      }
    );
  }

  public login(username: string, password: string): Observable<LoginInfo> {
    console.log('logging in...', environment.baseUrl + this.toontownUrl + '/login');
    const payload = JSON.stringify({'username': username, 'password': password});
    return this.http.post<LoginInfo>(
      environment.baseUrl + this.toontownUrl + '/login',
      payload,
      {
        headers: environment.headers,
        withCredentials: environment.withCredentials
      }
    )
  }

  public updateQueue(queueToken: string): Observable<LoginInfo> {
    console.log('updating queue...');
    const payload = JSON.stringify({'queueToken': queueToken});
    return this.http.post<LoginInfo>(
      environment.baseUrl + this.toontownUrl + '/updateQueue',
      payload,
      {
        headers: environment.headers,
        withCredentials: environment.withCredentials
      }
    )
  }
}
