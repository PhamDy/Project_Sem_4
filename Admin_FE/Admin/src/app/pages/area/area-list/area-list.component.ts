import { Component, OnInit } from '@angular/core';
import { TimeFrame } from 'src/app/models/timeFrame';
import { TimeFrameServiceService } from '../../timeFrame/timeFrameService.service';
import { Router } from '@angular/router';
import { AreaService } from '../area.service';

@Component({
  selector: 'app-area-list',
  templateUrl: './area-list.component.html',
  styleUrls: ['./area-list.component.css'],
})
export class AreaListComponent implements OnInit {
  timeFrameList: TimeFrame[] = [];
  areas: any[] = [];
  constructor(
    private timeService: TimeFrameServiceService,
    private router: Router,
    private areaService: AreaService
  ) {}

  ngOnInit(): void {
    this.timeService.getListTimeFrame().subscribe((res) => {
      this.timeFrameList = res;
    });

    this.areaService
      .getAllArea({
        page: 0,
        size: 10,
      })
      .subscribe(
        (data) => {
          this.areas = data.content;
        },
        (error) => {
          console.error('Error fetching districts', error);
        }
      );
  }
}
