import { Component, OnInit } from '@angular/core';
import { Status } from 'src/app/models/status';
import { ToontownService } from 'src/app/services/toontown.service';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {
  status!: Status;

  constructor(private toontownService: ToontownService) { }

  ngOnInit(): void {
    this.toontownService.getStatus().subscribe((status) => this.status = status);
  }

}
