import {Component, OnDestroy, OnInit} from '@angular/core';
import {ToonStats} from 'src/app/models/toonstats';
import {ToontownService} from 'src/app/services/toontown.service';
import {map, Subject, tap, timer} from "rxjs";

@Component({
  selector: 'app-toonstats',
  templateUrl: './toonstats.component.html',
  styleUrls: ['./toonstats.component.css']
})
export class ToonStatsComponent implements OnInit, OnDestroy {
  timer: any;

  toonstats: ToonStats | null = null;

  constructor(private toontownService: ToontownService) {
  }

  ngOnInit(): void {
    this.timer = timer(0, 10000).pipe(
      tap(() => console.log('getting field offices')),
      map(() => {
        this.toontownService.getToonStats().subscribe((toonstats) => this.toonstats = toonstats);

        return new Subject();
      }),
    ).subscribe();
  }

  getPercent(num1: number | undefined, num2: number | undefined): string {
    if (num1 && num2) {
      return ((num1 / num2) * 100).toFixed(2) + "%";
    }

    return "";
  }

  ngOnDestroy() {
    this.timer.unsubscribe();
  }
}
