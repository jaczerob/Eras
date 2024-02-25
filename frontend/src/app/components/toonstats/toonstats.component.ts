import {Component, OnInit} from '@angular/core';
import {ToonStats} from 'src/app/models/toonstats';
import {ToontownService} from 'src/app/services/toontown.service';
import {ApolloQueryResult} from "@apollo/client";
import {TTRPullDataQuery} from "../../models/ttr-pull-data";

@Component({
  selector: 'app-toonstats',
  templateUrl: './toonstats.component.html',
  styleUrls: ['./toonstats.component.css']
})
export class ToonStatsComponent implements OnInit {
  toonStats: ToonStats | null = null;

  constructor(private toontownService: ToontownService) {
  }

  ngOnInit(): void {
    this.toontownService.getToonStatsPageGQL().valueChanges.subscribe((result: ApolloQueryResult<TTRPullDataQuery>) => {
      if (result.errors) {
        alert("Error fetching news feed: " + result.errors);
        return;
      }

      this.toonStats = result.data.pullData.toonStats;
    });
  }

  getPercent(num1: number | undefined, num2: number | undefined): string {
    if (num1 && num2) {
      return ((num1 / num2) * 100).toFixed(2) + "%";
    }

    return "";
  }
}
