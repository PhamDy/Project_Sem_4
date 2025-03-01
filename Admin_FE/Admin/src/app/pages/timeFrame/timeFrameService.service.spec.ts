/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { TimeFrameServiceService } from './timeFrameService.service';

describe('Service: TimeFrameService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TimeFrameServiceService]
    });
  });

  it('should ...', inject([TimeFrameServiceService], (service: TimeFrameServiceService) => {
    expect(service).toBeTruthy();
  }));
});
