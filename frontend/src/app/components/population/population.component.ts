import { Component, OnInit } from '@angular/core';
import { District } from 'src/app/models/district';
import { Population } from 'src/app/models/population';
import { ToontownService } from 'src/app/services/toontown.service';

@Component({
  selector: 'app-population',
  templateUrl: './population.component.html',
  styleUrls: ['./population.component.css']
})
export class PopulationComponent implements OnInit {
  population!: Population

  constructor(private toontownService: ToontownService) { }

  ngOnInit(): void {
    this.toontownService.getPopulation().subscribe((population) => this.population = population);
  }

  getColor(district: District): string {
    if (district.status === District.DRAINING) {
      return District.DRAINING_COLOR;
    } else if (district.status in [District.CLOSED, District.OFFLINE]) {
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
