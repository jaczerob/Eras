import { Component, OnInit } from '@angular/core';
import { FieldOffices } from 'src/app/models/field-offices';
import { ToontownService } from 'src/app/services/toontown.service';
import {FieldOffice} from "../../models/field-office";

@Component({
  selector: 'app-field-offices',
  templateUrl: './field-offices.component.html',
  styleUrls: ['./field-offices.component.css']
})
export class FieldOfficesComponent implements OnInit {
  fieldOffices: FieldOffices | null = null;
  fieldOfficesMap: Map<String, FieldOffice> = new Map<String, FieldOffice>();

  constructor(private toontownService: ToontownService) { }

  ngOnInit(): void {
    this.toontownService.getFieldOffices().subscribe((fieldOffices) => {
      this.fieldOffices = fieldOffices;
      this.fieldOfficesMap = fieldOffices.fieldOffices
    });
  }
}
