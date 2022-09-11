import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FieldOfficesComponent } from './field-offices.component';

describe('FieldOfficesComponent', () => {
  let component: FieldOfficesComponent;
  let fixture: ComponentFixture<FieldOfficesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FieldOfficesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FieldOfficesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
