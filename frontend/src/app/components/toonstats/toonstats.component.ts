import { Component, OnInit } from '@angular/core';
import { ToonStats } from 'src/app/models/toonstats';
import { ToontownService } from 'src/app/services/toontown.service';

@Component({
  selector: 'app-toonstats',
  templateUrl: './toonstats.component.html',
  styleUrls: ['./toonstats.component.css']
})
export class ToonStatsComponent implements OnInit {
  toonstats: ToonStats | null = null;

  constructor(private toontownService: ToontownService) { }

  ngOnInit(): void {
    this.toontownService.getToonStats().subscribe((toonstats) => this.toonstats = toonstats);
  }

  getPercent(num1: number | undefined, num2: number | undefined): string {
    if (num1 && num2) {
      return ((num1 / num2) * 100).toFixed(2) + "%";
    }

    return "";
  }
}
