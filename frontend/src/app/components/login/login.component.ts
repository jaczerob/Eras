import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Status } from "src/app/models/status";
import { ToontownService } from "src/app/services/toontown.service";

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
    open('toontown://')
  }
}
