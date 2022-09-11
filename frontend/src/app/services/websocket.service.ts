import { Injectable } from '@angular/core';
import { LoginInfo } from '../models/login-info';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private socket: WebSocket;

  constructor() {
    this.socket = new WebSocket('ws://localhost:3000')
    this.socket.onopen = function() {
      console.log('connected to %s', this.url)
    }

    this.socket.onclose = function(evt) {
      console.log('disconnected from %s (%s)', this.url, evt.code)
    }
  }

  launch(loginInfo: LoginInfo) {
    const payload = JSON.stringify({'gameserver': loginInfo.gameserver, 'cookie': loginInfo.cookie});
    console.log('sending to %s: %s', this.socket.url, payload)

    this.socket.send(payload)
  }
}
