import {Component, OnInit} from '@angular/core';
import {FieldOffices} from 'src/app/models/field-offices';
import {ToontownService} from 'src/app/services/toontown.service';
import {FieldOffice} from "../../models/field-office";
import {ApolloQueryResult} from "@apollo/client";
import {TTRPullDataQuery} from "../../models/ttr-pull-data";

@Component({
  selector: 'app-field-offices',
  templateUrl: './field-offices.component.html',
  styleUrls: ['./field-offices.component.css']
})
export class FieldOfficesComponent implements OnInit {
  fieldOffices: FieldOffices | null = null;
  fieldOfficesMap: FieldOffice[] = [];

  constructor(private toontownService: ToontownService) {
  }

  ngOnInit(): void {
    this.toontownService.getFieldOfficesPageGQL().valueChanges.subscribe((result: ApolloQueryResult<TTRPullDataQuery>) => {
      if (result.errors) {
        alert("Error fetching news feed: " + result.errors);
        return;
      }

      this.fieldOffices = result.data.pullData.fieldOffices;
      this.fieldOfficesMap = this.fieldOffices.fieldOffices;
    });
  }
}
