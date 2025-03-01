import { Component, OnInit } from '@angular/core';
import { TimeFrameServiceService } from '../timeFrameService.service';
import { Router } from '@angular/router';
import { TimeFrame } from 'src/app/models/timeFrame';

@Component({
  selector: 'app-time-frame-list',
  templateUrl: './time-frame-list.component.html',
  styleUrls: ['./time-frame-list.component.css']
})
export class TimeFrameListComponent implements OnInit {

  timeFrameList: TimeFrame[] = [];

  constructor(private timeService: TimeFrameServiceService, private router: Router) { }

  ngOnInit(): void {
    this.timeService.getListTimeFrame().subscribe(res => {
      this.timeFrameList = res;
    })
  }

}
