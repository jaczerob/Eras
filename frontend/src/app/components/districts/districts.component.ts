import { Component, OnInit } from '@angular/core';
import { District } from 'src/app/models/district';
import { Districts } from 'src/app/models/districts';
import { Invasion } from 'src/app/models/invasion';
import { ToontownService } from 'src/app/services/toontown.service';

@Component({
  selector: 'app-districts',
  templateUrl: './districts.component.html',
  styleUrls: ['./districts.component.css']
})
export class DistrictsComponent implements OnInit {
  districts!: Districts

  constructor(private toontownService: ToontownService) { }

  ngOnInit(): void {
    this.toontownService.getDistricts().subscribe((districts) => this.districts = districts);
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

  getWidth(invasion: Invasion): string {
    return (invasion.cogsDefeated / invasion.totalCogs) * 100 + '%';
  }
}
