import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { News } from 'src/app/models/news';
import { ToontownService } from 'src/app/services/toontown.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class NewsComponent implements OnInit {
  news!: News;

  constructor(private toontown: ToontownService) { }

  ngOnInit(): void {
    this.toontown.getNews().subscribe((news) => this.news = news);
  }
}
