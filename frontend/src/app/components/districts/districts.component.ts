import {Component, OnInit} from '@angular/core';
import {District} from 'src/app/models/district';
import {Districts} from 'src/app/models/districts';
import {ToontownService} from 'src/app/services/toontown.service';
import {ApolloQueryResult} from "@apollo/client";
import {TTRPullDataQuery} from "../../models/ttr-pull-data";

@Component({
  selector: 'app-districts',
  templateUrl: './districts.component.html',
  styleUrls: ['./districts.component.css']
})
export class DistrictsComponent implements OnInit {
  districts: Districts | null = null;
  districtsArr: District[] = [];

  constructor(private toontownService: ToontownService) {
  }

  ngOnInit(): void {
    this.toontownService.getDistrictsPageGQL().valueChanges.subscribe((result: ApolloQueryResult<TTRPullDataQuery>) => {
      if (result.errors) {
        alert("Error fetching news feed: " + result.errors);
        return;
      }

      this.districts = result.data.pullData.districts;
      this.districtsArr = this.districts.districts;
    });
  }

  getColor(district: District): string {
    if (district.districtStatus === District.DRAINING) {
      return District.DRAINING_COLOR;
    } else if (district.districtStatus in [District.CLOSED, District.OFFLINE]) {
      return District.OFFLINE_COLOR;
    } else if (district.population >= District.FULL) {
      return District.FULL_COLOR;
    } else if (district.population >= District.BUSY) {
      return District.BUSY_COLOR;
    } else {
      return District.EMPTY_COLOR;
    }
  }
}
