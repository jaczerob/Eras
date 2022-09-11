import { Component, OnInit } from '@angular/core';
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

  getColor(population: Number): string {
    if (population >= 500) {
      return 'red';
    } else if (population >= 300) {
      return 'green';
    } else {
      return 'blue';
    }
  }
}
