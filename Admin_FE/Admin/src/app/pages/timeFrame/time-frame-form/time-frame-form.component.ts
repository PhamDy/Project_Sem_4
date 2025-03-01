import { Component, OnInit } from '@angular/core';
import { TimeFrameServiceService } from '../timeFrameService.service';
import { KeyValue } from 'src/app/models/keyValue';

@Component({
  selector: 'app-time-frame-form',
  templateUrl: './time-frame-form.component.html',
  styleUrls: ['./time-frame-form.component.css']
})
export class TimeFrameFormComponent implements OnInit {

  keyValue: KeyValue[] = [];

  constructor(private timeService: TimeFrameServiceService) { }

  ngOnInit() {
    this.timeService.getListType().subscribe(res => {
      this.keyValue = res;
    })
  }

}
