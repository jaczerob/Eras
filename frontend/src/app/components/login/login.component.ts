import { Buffer } from "buffer";
import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  })

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

    const socket = new WebSocket('ws://localhost:4500/ws/toontown');
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
