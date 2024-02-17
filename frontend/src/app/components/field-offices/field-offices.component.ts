import {Component, OnDestroy, OnInit} from '@angular/core';
import {FieldOffices} from 'src/app/models/field-offices';
import {ToontownService} from 'src/app/services/toontown.service';
import {FieldOffice} from "../../models/field-office";
import {map, Subject, tap, timer} from "rxjs";

@Component({
  selector: 'app-field-offices',
  templateUrl: './field-offices.component.html',
  styleUrls: ['./field-offices.component.css']
})
export class FieldOfficesComponent implements OnInit, OnDestroy {
  timer: any;

  fieldOffices: FieldOffices | null = null;
  fieldOfficesMap: Map<String, FieldOffice> = new Map<String, FieldOffice>();

  constructor(private toontownService: ToontownService) {
  }

  ngOnInit(): void {
    this.timer = timer(0, 10000).pipe(
      tap(() => console.log('getting field offices')),
      map(() => {
        this.toontownService.getFieldOffices().subscribe((fieldOffices) => {
          this.fieldOffices = fieldOffices;
          this.fieldOfficesMap = fieldOffices.fieldOffices;
        });

        return new Subject();
      }),
    ).subscribe();
  }

  ngOnDestroy() {
    this.timer.unsubscribe();
  }
}
