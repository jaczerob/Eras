import { TestBed } from '@angular/core/testing';

import { ToontownService } from './toontown.service';

describe('ToontownService', () => {
  let service: ToontownService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ToontownService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
