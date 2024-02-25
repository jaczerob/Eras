import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {News} from 'src/app/models/news';
import {ToontownService} from 'src/app/services/toontown.service';
import {ReleaseNotes} from "../../models/release-notes";
import {Status} from "../../models/status";
import {Districts} from "../../models/districts";
import {ApolloQueryResult} from "@apollo/client";
import {TTRPullDataQuery} from "../../models/ttr-pull-data";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class NewsComponent implements OnInit {
  districts: Districts | null = null;
  news: News | null = null;
  releaseNotes: ReleaseNotes | null = null;
  formattedReleaseNotes: Map<String, String> = new Map<String, String>();
  status: Status | null = null;

  constructor(private toontownService: ToontownService) {
  }

  ngOnInit(): void {
    this.toontownService.getNewsPageGQL().valueChanges.subscribe((result: ApolloQueryResult<TTRPullDataQuery>) => {
      if (result.errors) {
        alert("Error fetching news feed: " + result.errors);
        return;
      }

      let newsFeed = result.data.pullData;
      this.districts = newsFeed.districts;
      this.news = newsFeed.news;
      this.releaseNotes = newsFeed.releaseNotes;
      this.status = newsFeed.status;

      this.formattedReleaseNotes = new Map<String, String>();
      let categories = this.releaseNotes.body.split(/\r\n\r\n/);
      categories.forEach((category) => {
        let parts = category.split(/\r\n/);
        let title = parts[0].substring(1);

        let description = '';
        parts.slice(1).forEach((part) => description += (part.substring(2) + '<br>'));

        this.formattedReleaseNotes.set(title, description);
      });
    });
  }
}
