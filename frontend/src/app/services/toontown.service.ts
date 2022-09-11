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
    return this.get<Population>('/population');
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

  public login(username: string, password: string): Observable<LoginInfo> {
    console.log('logging in...');
    const payload = JSON.stringify({'username': username, 'password': password});
    return this.post<LoginInfo>('/login', payload);
  }

  public updateQueue(queueToken: string): Observable<LoginInfo> {
    console.log('updating queue...');
    const payload = JSON.stringify({'queueToken': queueToken});
    return this.post<LoginInfo>('/updateQueue', payload);
  }

  public authenticate(appToken: string, authToken: string): Observable<LoginInfo> {
    console.log('authenticating...');
    const payload = JSON.stringify({'appToken': appToken, 'authToken': authToken});
    return this.post<LoginInfo>('/authenticate', payload);
  }

  private post<T>(endpoint: string, payload: string): Observable<T> {
    return this.http.post<T>(
      environment.baseUrl + this.toontownUrl + endpoint,
      payload,
      {
        headers: environment.headers,
        withCredentials: environment.withCredentials
      }
    )
  }

  private get<T>(endpoint: string): Observable<T> {
    return this.http.get<T>(
      environment.baseUrl + this.toontownUrl + endpoint,
      {
        headers: environment.headers,
        withCredentials: environment.withCredentials
      }
    )
  }
}
