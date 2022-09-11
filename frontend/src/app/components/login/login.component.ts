import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { delay, mergeMap, of } from 'rxjs';
import { LoginInfo } from 'src/app/models/login-info';
import { ToontownService } from 'src/app/services/toontown.service';
import { WebsocketService } from 'src/app/services/websocket.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnDestroy {
  banner!: string;

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  })

  constructor(private ws: WebsocketService, private toontown: ToontownService) {}

  get username() {
    return this.loginForm.get('username')?.value || '';
  }

  get password() {
    return this.loginForm.get('password')?.value || '';
  }

  ngOnDestroy() {
    this.ws.close();
  }

  onSubmit(): void {
    if (this.username === '' || this.password === '') {
      alert('Please enter a username and password.');
      return;
    }

    this.banner = 'Logging in...';

    this.toontown.login(this.username, this.password).pipe(
      mergeMap((loginInfo) => {
        if (loginInfo.success in ['false', 'true', 'partial']) {
          return of(loginInfo);
        } else {
          this.banner = 'In queue...';
          return this.toontown.updateQueue(loginInfo.queueToken).pipe(delay(5000));
        }
      })
    ).subscribe((loginInfo) => {
      if (loginInfo.success in ['false', 'partial']) {
        this.banner = loginInfo.banner;
        return;
      }
      
      this.ws.launch(loginInfo);
      this.banner = 'Logged in!';
      setTimeout(() => {
        this.banner = '';
      }, 1000);
    })
  }
}
