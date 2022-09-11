import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { delay, iif, mergeMap, of, tap } from 'rxjs';
import { ToontownService } from 'src/app/services/toontown.service';
import { WebsocketService } from 'src/app/services/websocket.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{

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

  onSubmit(): void {
    if (this.username === '' || this.password === '') {
      alert('Please enter a username and password.');
      return;
    }

    this.toontown.login(this.username, this.password).pipe(
      mergeMap((loginInfo) => iif(
          () => loginInfo.queueToken == null, 
          of(loginInfo),
          this.toontown.updateQueue(loginInfo.queueToken)
        )
      )
    ).subscribe((loginInfo) => {
      console.log('logging in', loginInfo);
      this.ws.launch(loginInfo);
    })
  }
}
