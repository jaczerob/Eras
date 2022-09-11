import { Component, OnInit } from '@angular/core';
import { FieldOffices } from 'src/app/models/field-offices';
import { ToontownService } from 'src/app/services/toontown.service';

@Component({
  selector: 'app-field-offices',
  templateUrl: './field-offices.component.html',
  styleUrls: ['./field-offices.component.css']
})
export class FieldOfficesComponent implements OnInit {
  fieldOffices!: FieldOffices;

  constructor(private toontownService: ToontownService) { }

  ngOnInit(): void {
    this.toontownService.getFieldOffices().subscribe((fieldOffices) => this.fieldOffices = fieldOffices);
  }
}
