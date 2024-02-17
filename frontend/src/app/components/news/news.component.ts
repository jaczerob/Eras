import {Component, ElementRef, OnInit, Renderer2, ViewChild, ViewEncapsulation} from '@angular/core';
import { News } from 'src/app/models/news';
import { ToontownService } from 'src/app/services/toontown.service';
import {ReleaseNotes} from "../../models/release-notes";
import {Status} from "../../models/status";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class NewsComponent implements OnInit {
  news: News | null = null;
  releaseNotes: ReleaseNotes | null = null;
  formattedReleaseNotes: Map<String, String> = new Map<String, String>();
  status: Status | null = null;

  constructor(private toontownService: ToontownService, private renderer: Renderer2) { }

  ngOnInit(): void {
    this.toontownService.getStatus().subscribe((status) => {
      this.status = status;
    });

    this.toontownService.getNews().subscribe((news) => {
      this.news = news;
    });

    this.toontownService.getReleaseNotes().subscribe((releaseNotes) => {
      this.releaseNotes = releaseNotes;
      this.formattedReleaseNotes = new Map<String, String>();

      let categories = releaseNotes.body.split(/\r\n\r\n/);
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
