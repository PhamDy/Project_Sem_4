import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AreaServicesService } from '../../../services/area-services.service';

@Component({
  selector: 'app-booking-detail-area',
  templateUrl: './booking-detail-area.component.html',
  styleUrl: './booking-detail-area.component.css',
})
export class BookingDetailAreaComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private areaServicesService: AreaServicesService,
    private router: Router
  ) {}
  areaId: string | null = null;
  areaDetail: any = null;

  img: any = 'https://imgur.com/JMP9KcJ.png';

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      this.areaId = params.get('id');
      if (this.areaId) {
        this.getAreaById(this.areaId);
      }
    });
  }

  getAreaById(id: string) {
    this.areaServicesService.getDetailById(id).subscribe((data) => {
      if (data) {
        this.areaDetail = data;
      }
    });
  }

  viewDetail(idField: string) {
    this.router.navigate([`/booking-area/${idField}`]);
  }

  viewTournament(idArea: any) {
    this.router.navigate([`/tournament/${idArea}`]);
  }
}
