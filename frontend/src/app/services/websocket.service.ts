import { Injectable } from '@angular/core';
import { LoginInfo } from '../models/login-info';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private _socket!: WebSocket;

  constructor() {}
  
  get socket(): WebSocket {
    if (this._socket == null || this._socket.readyState !== WebSocket.OPEN) {
      const socket = new WebSocket('ws://localhost:3000');

      socket.onopen = function() {
        console.log('connected to %s', this.url);
      }

      socket.onclose = function(evt) {
        console.log('disconnected from %s (%s)', this.url, evt.code);
      }
      
      this._socket = socket;
    }

    return this._socket;
  }

  launch(loginInfo: LoginInfo) {
    const payload = JSON.stringify({'gameserver': loginInfo.gameserver, 'cookie': loginInfo.cookie});
    console.log('sending to %s: %s', this.socket.url, payload);

    this.socket.send(payload);
  }

  close() {
    if (this.socket.readyState !in [WebSocket.CLOSED, WebSocket.CLOSING])
      this.socket.close();
  }
}
