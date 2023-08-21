import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToonStatsComponent } from './toonstats.component';

describe('ToonStatsComponent', () => {
  let component: ToonStatsComponent;
  let fixture: ComponentFixture<ToonStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ToonStatsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ToonStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
