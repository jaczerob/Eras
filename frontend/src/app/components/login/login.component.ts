import { Buffer } from 'buffer';
import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Status } from "src/app/models/status";
import { ToontownService } from "src/app/services/toontown.service";
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  status!: Status;

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  })

  constructor(private toontown: ToontownService) { }

  ngOnInit(): void {
    this.toontown.getStatus().subscribe((status) => this.status = status);
  }

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

    const username = this.username
    const password = this.password

    const socket = new WebSocket(environment.wsUrl);
    socket.onmessage = function (msg) {
      console.log('message from ws: %s', msg.data)
    }

    socket.onclose = function (ev) {
      console.log('socket connection closed')
    }

    socket.onopen = function (_) {
      console.log('socket connection opened')

      const encryptedDetails = Buffer.from(`${username}:${password}`).toString('base64')
      socket.send(encryptedDetails)

      socket.close()
    }
  }
}
