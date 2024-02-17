import {Component, OnDestroy, OnInit} from '@angular/core';
import {District} from 'src/app/models/district';
import {Districts} from 'src/app/models/districts';
import {ToontownService} from 'src/app/services/toontown.service';
import {map, Subject, tap, timer} from "rxjs";

@Component({
  selector: 'app-districts',
  templateUrl: './districts.component.html',
  styleUrls: ['./districts.component.css']
})
export class DistrictsComponent implements OnInit, OnDestroy {
  timer: any;

  districts: Districts | null = null;
  districtsArr: District[] = [];

  constructor(private toontownService: ToontownService) {
  }

  ngOnInit(): void {
    this.timer = timer(0, 10000).pipe(
      tap(() => console.log('getting districts')),
      map(() => {
        this.toontownService.getDistricts().subscribe((districts) => {
          districts.districts.sort(function (a, b) {
            return b.population - a.population;
          });
          this.districts = districts;
          this.districtsArr = districts.districts;
        });

        return new Subject();
      }
    )).subscribe();
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

  ngOnDestroy() {
    this.timer.unsubscribe();
  }
}
